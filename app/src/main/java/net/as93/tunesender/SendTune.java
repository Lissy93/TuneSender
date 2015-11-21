package net.as93.tunesender;

import android.telephony.SmsManager;

public class SendTune {

    private Tune tune;

    public SendTune(Tune tune) {
        this.tune = tune;
    }


    /**
     * Sends the SMS
     * @param phoneNo the users phone number to send it to
     * @return true if sent was successful, else false
     */
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
