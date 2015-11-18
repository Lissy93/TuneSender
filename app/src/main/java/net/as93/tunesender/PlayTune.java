package net.as93.tunesender;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class PlayTune extends DialogFragment{

    ImageView staveImageView;
    Context c;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(getArguments().getString("TITLE"));
//        View v =  inflater.inflate(R.layout.fragment_play_tune, container, false);


        View v = new TuneView(getActivity());

        return v;
    }




    /**
     * Creates a new instance
     * @param context Context
     * @param rawTune String value of Tune
     * @return PlayTune instance
     */
    public static PlayTune newInstance(Context context, String rawTune, String t) {
        PlayTune playTuneDialog = new PlayTune();
        Bundle args = new Bundle();
        args.putString("RAW_TUNE", rawTune);
        args.putString("TITLE", t);
        playTuneDialog.setArguments(args);
        return playTuneDialog;
    }


}
