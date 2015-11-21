package net.as93.tunesender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Tone{

    private String strTone;

    private double quaverDuration = 0.5;

    private int duration;   // (1|2|3|4|6|8)
    private char note;      // (A|B|C|D|E|F|G)
    private char notation;  // (b|#|x)
    private int pitch;      // (4|5|6)

    private HashMap<String, Double> frequencies = new HashMap<>();

    protected Tone(String strTone) {
        this.strTone = strTone;
        makeToneFromStr(); // Sets the class variables with components
        makeFrequencyLookup();
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

    public double getPlayableDuration(){
        return duration * quaverDuration;
    }

    public double getFrequency(){
        Double d = frequencies.get(strTone.substring(1));
        if(d == null) return 0;
        else return d;
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

        strTone.replaceAll("\\s+", ""); // Remove training white spaces

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
        if(isToneValid()) {
            this.duration = Integer.parseInt(strTone.substring(0, 1));
            this.note = strTone.charAt(1);
            this.pitch = Integer.parseInt("" + strTone.charAt(strTone.length() - 1));
            if (strTone.contains("b")) this.notation = 'b';
            else if (strTone.contains("#")) this.notation = '#';
            else this.notation = 'x';
        }
    }

    private void makeFrequencyLookup(){
        frequencies.put("C4", 261.6);
        frequencies.put("C#4", 277.2);
        frequencies.put("D4", 293.7);
        frequencies.put("Eb4", 311.1);
        frequencies.put("E4", 329.6);
        frequencies.put("F4", 349.2);
        frequencies.put("F#4", 370.0);
        frequencies.put("G4", 392.0);
        frequencies.put("G#4", 415.3);
        frequencies.put("G#4", 440.0);
        frequencies.put("Bb4", 466.2);
        frequencies.put("B4", 493.9);

        frequencies.put("C5", 523.3);
        frequencies.put("C#5", 554.4);
        frequencies.put("D5", 587.3);
        frequencies.put("Eb5", 622.3);
        frequencies.put("E5", 659.3);
        frequencies.put("F5", 698.5);
        frequencies.put("F#5", 740.0);
        frequencies.put("G5", 784.0);
        frequencies.put("G#5", 830.6);
        frequencies.put("G#5", 880.0);
        frequencies.put("Bb5", 932.3);
        frequencies.put("B5", 987.8);

        frequencies.put("C6", 1047.0);
        frequencies.put("C#6", 1109.0);
        frequencies.put("D6", 1175.0);
        frequencies.put("Eb6", 1245.0);
        frequencies.put("E6", 1319.0);
        frequencies.put("F6", 1397.0);
        frequencies.put("F#6", 1480.0);
        frequencies.put("G6", 1568.0);
        frequencies.put("G#6", 1661.0);
        frequencies.put("G#6", 1760.0);
        frequencies.put("Bb6", 1865.0);
        frequencies.put("B6", 1976.0);
    }
}