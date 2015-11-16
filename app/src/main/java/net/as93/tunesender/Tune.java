package net.as93.tunesender;


public class Tune {

    private String rawTune;

    public Tune(String rawTune) {
        this.rawTune = rawTune;
    }

    public String getRawTune() {
        return rawTune;
    }

    public void setRawTune(String rawTune) {
        this.rawTune = rawTune;
    }

    public boolean isTuneValid(){
        //TODO
        return true;
    }

    

}
