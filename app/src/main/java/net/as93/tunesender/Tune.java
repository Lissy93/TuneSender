package net.as93.tunesender;


import java.util.ArrayList;
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
        return rawTune.matches("^([1-4|6|8][A-Ga-g][bB#]*[0-9](\\s)?){1,12}$");
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
                (new Tone(tone)).whyIsToneInvalid();
            }
            if(rawTune.length()<2){tuneValidityStatus = "Tune can not be empty"; }
        }
    }


    private class Tone{

        private String strTone;

        private int duration;
        private char note;
        private String pitch;

        protected Tone(String strTone) {
            this.strTone = strTone;
            if(isTuneValid()) {
                makeToneFromStr(); // Sets the class variables with components
            }
        }


        /**
         * Checks that a tone is of a valid format, including:
         * Has a valid length, begins with a valid duration,
         * contains a valid music note, a valid scientific pitch
         * @return true if tone is valid, else false
         */
        protected boolean isToneValid() {
            whyIsToneInvalid(); // Sets parent class var with message for user
            return strTone.matches("^[1-4|6|8][A-Ga-g][bB#]*[0-9]$");
        }


        /**
         * Determines why the given string is invalid
         * Sets the class variable tuneValidityStatus with message for user
         */
        protected void whyIsToneInvalid(){

            Set<String> validDurations = new HashSet<>(Arrays.asList(
                    new String[]{"1", "2", "3", "4", "6", "8"}
            ));

            ArrayList<String> validNotes = new ArrayList<>(Arrays.asList(
                    new String[]{"A", "B", "C", "D", "E", "F", "G"}
            ));

            strTone.replaceAll("\\s+",""); // Remove training white spaces

            if(strTone.length() < 3){
                tuneValidityStatus = "Notes must be at least 3 characters";
            }
            else if(!validDurations.contains(strTone.substring(0, 1))){
                tuneValidityStatus = "Note must begin with a valid duration";
            }
            else if(!validNotes.contains(strTone.substring(1, 2).toUpperCase())){
                tuneValidityStatus = "Note must contain a valid music note";
            }
            else if(!strTone.substring(strTone.length() - 1).matches("^-?\\d+$")){
                tuneValidityStatus = "A valid number for pitch must be specified";
            }
            else if(strTone.length()>4){
                tuneValidityStatus = "Note is too long";
            }
        }


        /**
         * Breaks the raw tone string down into individual components
         * @pre raw tone must be valid
         * @post the inner class variables of tone will have values
         */
        private void makeToneFromStr(){
            this.duration = Integer.parseInt(strTone.substring(0, 1));
            this.note = strTone.charAt(1);
            this.pitch = strTone.substring(2,strTone.length());
        }
    }



}
