package net.as93.tunesender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;


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
        canvas.drawBitmap(stave, null, new Rect(0,150,900,400), null);
    }




}
