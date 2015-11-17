package net.as93.tunesender;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Tune {

    private String rawTune;
    private String tuneValidityStatus = "";

    public Tune(String rawTune) {
        this.rawTune = rawTune;
    }

    public String getRawTune() {
        return rawTune;
    }

    public void setRawTune(String rawTune) {
        this.rawTune = rawTune;
    }

    public String getTuneValidityStatus() {
        isTuneValid();
        return tuneValidityStatus;
    }

    /**
     * Checks the syntax of the raw tune message to determine if it is valid
     * @return true if tune is valid and could be played, or false if invalid
     */
    public boolean isTuneValid(){

        tuneValidityStatus = "Tune is valid.";

        if(rawTune.equals("")){
            tuneValidityStatus = "Tune can not be empty";
            return false;
        }

        for(String note : rawTune.split(" ")){
            Tone t = new Tone(note);
            if(!t.isToneValid()){
                return false;
            }
        }

        return true;
    }

    private class Tone{

        private String strTone;

        private int duration;
        private char note;
        private char pitchSymbol;
        private int pitch;

        public Tone(String strTone) {
            this.strTone = strTone;
        }

        public boolean isToneValid(){

            Set<String> validDurations = new HashSet<>(Arrays.asList(
                    new String[]{"1", "2", "3", "4", "6", "8"}
            ));

            Set<String> validNotes = new HashSet<>(Arrays.asList(
                    new String[]{"A", "B", "C", "D", "E", "F", "G"}
            ));

            if(strTone.length() < 3){
                tuneValidityStatus = "Notes must be at least 3 characters";
                return false;
            }
            else if(!validDurations.contains(strTone.substring(0, 1))){
                tuneValidityStatus = "Note must begin with a valid duration";
                return false;
            }
            else if(!validNotes.contains(strTone.substring(1, 2).toUpperCase())){
                tuneValidityStatus = "Note must contain a valid music note";
                return false;
            }
            else if(!strTone.substring(strTone.length() - 1).matches("^-?\\d+$")){
                tuneValidityStatus = "A valid number for pitch must be specified";
                return false;
            }
            else if(strTone.length()>4){
                tuneValidityStatus = "Note is too long";
                return false;
            }
            return true;
        }

        private void makeToneFromStr(){

        }
    }



}
