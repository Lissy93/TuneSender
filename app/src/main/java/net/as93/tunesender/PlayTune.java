package net.as93.tunesender;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class PlayTune extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play_tune, container, false);
    }


    public static PlayTune newInstance(Context context, String rawTune) {

        PlayTune playTuneDialog = new PlayTune();
        Bundle args = new Bundle();
        args.putString("RAW_TUNE", rawTune);
        playTuneDialog.setArguments(args);

        return playTuneDialog;
    }


}
