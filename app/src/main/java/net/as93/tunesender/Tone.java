package net.as93.tunesender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Tone{

    private String strTone;

    private int duration;   // (1|2|3|4|6|8)
    private char note;      // (A|B|C|D|E|F|G)
    private char notation;  // (b|#|x)
    private int pitch;      // (4|5|6)

    protected Tone(String strTone) {
        this.strTone = strTone;
        makeToneFromStr(); // Sets the class variables with components
    }

    public String getStrTone() {
        return strTone;
    }

    public int getDuration() {
        return duration;
    }

    public char getNote() {
        return note;
    }

    public int getPitch() {
        return pitch;
    }

    public char getNotation() {
        return notation;
    }

    /**
     * Checks that a tone is of a valid format, including:
     * Has a valid length, begins with a valid duration,
     * contains a valid music note, a valid scientific pitch
     * @return true if tone is valid, else false
     */
    protected boolean isToneValid() {
        whyIsToneInvalid(); // Sets parent class var with message for user
        return strTone.matches("^[1-4|6|8][A-G][b#]?[0-9]$");
    }


    /**
     * Determines why the given string is invalid
     * Sets the class variable tuneValidityStatus with message for user
     */
    protected String whyIsToneInvalid(){

        String tuneValidityStatus = "";

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

        return  tuneValidityStatus;
    }


    /**
     * Breaks the raw tone string down into individual components
     * @pre raw tone must be valid
     * @post the inner class variables of tone will have values
     */
    private void makeToneFromStr(){
        this.duration = Integer.parseInt(strTone.substring(0, 1));
        this.note = strTone.charAt(1);
        this.pitch = Integer.parseInt(""+strTone.charAt(strTone.length()-1));
        if(strTone.contains("b")) this.notation = 'b';
        else if(strTone.contains("#")) this.notation = '#';
        else this.notation = 'x';
    }
}