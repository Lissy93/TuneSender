package net.as93.tunesender;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Tune {

    private String rawTune;
    private String tuneValidityStatus = "";
    private ArrayList<Tone> tones = new ArrayList<>();

    public Tune(String rawTune) {
        this.rawTune = rawTune;
        for(String tone: rawTune.split(" ")) tones.add(new Tone(tone));
    }

    public String getRawTune() {
        return rawTune;
    }


    public ArrayList<Tone> getTones() {
        return tones;
    }


    /**
     * Getter for tuneValiityStatus class variable
     * String description of if tune is valid
     * Calls makeTuneValidtyStatus() first to populate var
     * @return String description of is tune valid
     */
    public String getTuneValidityStatus() {
        makeTuneValidtyStatus();
        return tuneValidityStatus;
    }


    /**
     * Checks the syntax of the raw tune message to determine if it is valid
     * @return true if tune is valid and could be played, or false if invalid
     */
    public boolean isTuneValid(){
        return rawTune.matches("^([1-4|6|8][A-G][b#]?[4-6](\\s)?){1,7}$");
    }


    /**
     * Populates the tuneValidityStatus class variable with a description
     * of why the tune is valid or invalid ready to display to user
     */
    private void makeTuneValidtyStatus(){
        boolean valid = isTuneValid(); // Determine if tune is valid
        if(valid){tuneValidityStatus = "Tune is Valid"; }
        else{
            tuneValidityStatus = "Tune is Invalid";
            for(String tone: rawTune.split(" ")){
                String reason = (new Tone(tone)).whyIsToneInvalid();
                if(!reason.equals("")) tuneValidityStatus = reason;
            }
            if(rawTune.length()<2){tuneValidityStatus = "Tune can not be empty"; }
        }
    }



}
