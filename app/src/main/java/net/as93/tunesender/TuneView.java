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

    public TuneView(Context context) {
        super(context);
        tune = ((MainActivity)context).getLastTune();
        assignBitmaps();    
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO you can use tune to get the notes ready to display on the stave

        Bitmap stave = BitmapFactory
                .decodeResource(getContext().getResources(), R.drawable.stave);

        Bitmap crotchetDown = BitmapFactory
                .decodeResource(getContext().getResources(), R.drawable.crotchet_down);

        canvas.drawBitmap(stave, null, new Rect(0,150,900,400), null);

        int distance = 70;  // The distance between each note
        int width = 70;     // The width of each note
        int height = 190;   // The fixed height of each NORMAL note
        int top = 160;
        int left = 50;      // The left position (will be incremented by width)

        for(Tone tone: tune.getTones()){
            Rect r = new Rect(left,top,left+width,top+height);
            canvas.drawBitmap(getNoteFromDuration(tone.getDuration()), null, r, null);
            left +=distance;
        }


    }


    private Bitmap getNoteFromDuration(int duration){
        switch (duration){
            case(1):
                return bitmaps.get("crotchetDown");
            case(2):
                return bitmaps.get("dottedCrotchetDown");
            case(3):
                return bitmaps.get("dottedMinimDown");
            case(4):
                return bitmaps.get("quaverDown");
            default:
                return bitmaps.get("minimDown");
        }

    }

    private void assignBitmaps(){
        bitmaps.put("stave", makeBitmap(R.drawable.stave));
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


    private Bitmap makeBitmap(int res){
        return BitmapFactory.decodeResource(getContext().getResources(), res);
    }






}
