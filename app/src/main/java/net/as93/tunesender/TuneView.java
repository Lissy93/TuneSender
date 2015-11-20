package net.as93.tunesender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import java.util.ArrayList;
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

        canvas.drawBitmap(bitmaps.get("stave"), null, new Rect(0,155,900,400), null);
        canvas.drawBitmap(bitmaps.get("trebleClef"), null, new Rect(10,175,100,385), null);

        int distance = 80;  // The distance between each note
        int width = 80;     // The width of each note
        int height = 190;   // The fixed height of each NORMAL note
        int top = 160;      // Distance from top
        int left = 120;     // The left position (will be incremented by width)

        for(Tone tone: tune.getTones()){
            top = getTop(tone.getNote(), tone.getPitch());
            Rect r = new Rect(left,top,left+width,top+height);
            canvas.drawBitmap(getNoteFromDuration(tone.getDuration()), null, r, null);
            left +=distance;
        }


    }


    private Bitmap getNoteFromDuration(int duration){
        switch (duration){
            case(1): return bitmaps.get("quaverDown");
            case(2): return bitmaps.get("crotchetDown");
            case(3): return bitmaps.get("dottedCrotchetDown");
            case(4): return bitmaps.get("minimDown");
            case(6): return bitmaps.get("dottedMinimDown");
            case(8): return bitmaps.get("semibreve");
            default: return bitmaps.get("quaverDown");
        }
    }

    private int getTop(char note, int pitch){
        //                             A   B   C   D   E   F
        //int[] positions = new int[]{270,245,220,195,170,145};
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






}
