package net.as93.tunesender;


import android.telephony.SmsManager;

public class SendTune {

    private Tune tune;

    public SendTune(Tune tune) {
        this.tune = tune;
    }

    public boolean sendSMS(String phoneNo){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, tune.getRawTune(), null, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
