package net.as93.tunesender;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


public class ViewTune extends DialogFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(getArguments().getString("TITLE"));
        View v =  inflater.inflate(R.layout.fragment_play_tune, container, false);

        // Add the Stave View
        RelativeLayout layout = (RelativeLayout)v.findViewById(R.id.tuneLayout);
        View child = new StaveView(getActivity());
        layout.addView(child);

        // Call PlayTone on button press
        final Button button = (Button) v.findViewById(R.id.btnPlayTune);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playTune(((MainActivity) getActivity()).getLastTune());
            }
        });

        return v;
    }


    /**
     * Creates a new instance
     * @param context Context
     * @param rawTune String value of Tune
     * @return ViewTune instance
     */
    public static ViewTune newInstance(Context context, String rawTune, String t) {
        ViewTune viewTuneDialog = new ViewTune();
        Bundle args = new Bundle();
        args.putString("RAW_TUNE", rawTune);
        args.putString("TITLE", t);
        viewTuneDialog.setArguments(args);
        return viewTuneDialog;
    }


    /**
     * Creates an instance of the PlaySound class,
     * then plays a sound for every valid tone
     * @param tune Tune object containing a list of Tones
     */
    private void playTune(Tune tune){
        System.out.print(tune.getRawTune());
        PlaySound playSound = new PlaySound();
        for(Tone tone: tune.getTones()){
            double frequency = tone.getFrequency();
            if(frequency != 0)
                playSound.play(tone.getPlayableDuration(), frequency);
        }
    }

}