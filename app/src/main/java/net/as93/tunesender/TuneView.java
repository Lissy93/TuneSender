package net.as93.tunesender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import java.util.HashMap;


public class TuneView extends View {

    private Tune tune;
    private HashMap<String, Bitmap> bitmaps = new HashMap<>();
    private HashMap<String, Integer> notePositions = new HashMap<>();
    private char[] notes = new char[]{'A','B','C','D','E','F','G'};

    public TuneView(Context context) {
        super(context);
        tune = ((MainActivity)context).getLastTune();
        assignBitmaps();
        assignNotePositions();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the stave and treble clef
        canvas.drawBitmap(bitmaps.get("stave"), null, new Rect(0,155,900,400), null);
        canvas.drawBitmap(bitmaps.get("trebleClef"), null, new Rect(10, 175, 100, 385), null);

        // variables for drawing notes
        int distance = 80;  // The distance between each note
        int width = 80;     // The width of each note
        int height = 190;   // The fixed height of each NORMAL note
        int left = 120;     // The left position (will be incremented by width)
        int gap = 25;       // The gap between each note
        int dif = 50;       // Used for converting upsidedown notes round

        // Draw each note in the tune
        for(Tone tone: tune.getTones()){
            String direction  = isUpOrDown(tone.getNote(), tone.getPitch());
            Bitmap bitmap = getBitmapFromDuration(tone.getDuration(), direction);
            int top = getTop(tone.getNote(), tone.getPitch());
            int bottom = top+height;
            if(direction.equals("Up")){ // Display an upside down note
                top = top - height + gap + dif;
                bottom = bottom - height + dif;
            }
            Rect r = new Rect(left,top,left+width,bottom);
            canvas.drawBitmap(bitmap, null, r, null);

            left +=distance; // Increment the x coordinate for the next note

            char symbol = tone.getNotation();
            if(symbol =='b' || symbol == '#'){
                if(symbol == 'b') bitmap = bitmaps.get("flat");
                else bitmap = bitmaps.get("sharp");
                Rect r2 = new Rect(left,200,left+width,300);
                canvas.drawBitmap(bitmap, null, r2, null);
                left += distance;
            }

        }
    }


    private Bitmap getBitmapFromDuration(int duration, String direction){
        switch (duration){
            case(1): return bitmaps.get("quaver"+direction);
            case(2): return bitmaps.get("crotchet"+direction);
            case(3): return bitmaps.get("dottedCrotchet"+direction);
            case(4): return bitmaps.get("minim"+direction);
            case(6): return bitmaps.get("dottedMinim"+direction);
            case(8): return bitmaps.get("semibreve");
            default: return bitmaps.get("quaver"+direction);
        }
    }

    private int getTop(char note, int pitch){
        return notePositions.get(""+note+pitch);
    }

    private void assignBitmaps(){
        bitmaps.put("stave", makeBitmap(R.drawable.stave));
        bitmaps.put("trebleClef", makeBitmap(R.drawable.treble_clef));
        bitmaps.put("crotchetDown", makeBitmap(R.drawable.crotchet_down));
        bitmaps.put("crotchetUp", makeBitmap(R.drawable.crotchet_up));
        bitmaps.put("dottedCrotchetDown", makeBitmap(R.drawable.dotted_crotchet_down));
        bitmaps.put("dottedCrotchetUp", makeBitmap(R.drawable.dotted_crotchet_up));
        bitmaps.put("dottedMinimDown", makeBitmap(R.drawable.dotted_minim_down));
        bitmaps.put("dottedMinimUp", makeBitmap(R.drawable.dotted_minim_up));
        bitmaps.put("minimDown", makeBitmap(R.drawable.minim_down));
        bitmaps.put("minimUp", makeBitmap(R.drawable.minim_up));
        bitmaps.put("quaverDown", makeBitmap(R.drawable.quaver_down));
        bitmaps.put("quaverUp", makeBitmap(R.drawable.quaver_up));
        bitmaps.put("semibreve", makeBitmap(R.drawable.semibreve));
        bitmaps.put("flat", makeBitmap(R.drawable.flat));
        bitmaps.put("sharp", makeBitmap(R.drawable.sharp));
    }

    private void assignNotePositions(){
        int position = 0;
        for(int i =4; i<=6; i++){
            for(char note: notes){
                notePositions.put(""+note+i, position);
                position += 25;
            }
        }
    }

    private Bitmap makeBitmap(int res){
        return BitmapFactory.decodeResource(getContext().getResources(), res);
    }

    private String isUpOrDown(char note, int pitch){
        if(notePositions.get(""+note+pitch)>226) return "Up";
        else return "Down";
    }






}
