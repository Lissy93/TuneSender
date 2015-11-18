package net.as93.tunesender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;


public class TuneView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TuneView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap stave = BitmapFactory
                .decodeResource(getContext().getResources(), R.drawable.stave);
        canvas.drawBitmap(stave, null, new Rect(0,150,900,400), null);
    }




}
