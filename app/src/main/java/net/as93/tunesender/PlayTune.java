package net.as93.tunesender;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class PlayTune extends DialogFragment {

    private String title;

    public PlayTune(String title) {
        this.title = title;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(title);
        return inflater.inflate(R.layout.fragment_play_tune, container, false);
    }


    /**
     * Creates a new instance
     * @param context Context
     * @param rawTune String value of Tune
     * @return PlayTune instance
     */
    public static PlayTune newInstance(Context context, String rawTune, String t) {
        PlayTune playTuneDialog = new PlayTune(t);
        Bundle args = new Bundle();
        args.putString("RAW_TUNE", rawTune);
        playTuneDialog.setArguments(args);
        return playTuneDialog;
    }


}
