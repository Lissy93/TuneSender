package net.as93.tunesender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import java.util.ArrayList;


public class TuneView extends View {

    private Tune tune;

    public TuneView(Context context) {
        super(context);
        tune = ((MainActivity)context).getLastTune();
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

        int left = 80;

        for(Tone tone: tune.getTones()){
            canvas.drawBitmap(crotchetDown, null, new Rect(left,160,left+100,410), null);
            left +=100;
        }


    }








}
