package net.as93.tunesender;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText txtRawTune;
    private EditText txtPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set XML layout for main view
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar)); // toolbar

        // Set text inputs to class variables
        txtRawTune = (EditText)findViewById(R.id.txtRawTune);
        txtPhoneNumber = (EditText)findViewById(R.id.txtPhoneNum);

        // Add button press listeners
        findViewById(R.id.fab).setOnClickListener(this);
        findViewById(R.id.btnValidate).setOnClickListener(this);
        findViewById(R.id.btnPreview).setOnClickListener(this);
        findViewById(R.id.btnSend).setOnClickListener(this);
    }


    /**
     * Manages all click listeners
     * @param v the view that was pressed
     */
    @Override
    public void onClick(View v) {

        Tune currentTune = new Tune(txtRawTune.getText().toString());

        switch(v.getId()){
            case(R.id.fab): // The fab button was pressed
                showMessage(v, "No New SMS Tunes");
                break;
            case(R.id.btnValidate): // The validate input sms button
                showMessage(v, currentTune.getTuneValidityStatus());
                break;
            case(R.id.btnPreview): // The preview raw input button
                //TODO
                break;
            case(R.id.btnSend): // The send sms button
                sendTheMessage(v, currentTune);
                break;
        }

    }

    /**
     * Displays the Android Snackbar with a specified message
     * @param view the current view to display bar in
     * @param message a String value of the message to be shown
     */
    private void showMessage(View view, String message){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }


    /**
     * Checks to see if a phone number vaguely looks
     * like it could  possibly maybe be valid.
     */
    private boolean isPhoneNumValid(String phoneNum){
        return phoneNum.matches("^[0|\\+4{2}]7[0-9]{9}");
    }


    /**
     * Checks the phone number and SMS message
     * if everything cool, creates sms sending class and sends sms
     * then notifies the user that everything is done
     * @param v current View
     * @param currentTune Tune current tune
     */
    private void sendTheMessage(View v, Tune currentTune){
        String phoneNum = txtPhoneNumber.getText().toString(); // Get phone num

        if(!isPhoneNumValid(phoneNum)){ // phone num not looking good
            showMessage(v, "Please enter a valid mobile number");
        }
        else if(!currentTune.isTuneValid()){ // dou! that tune no valid
            showMessage(v, "Unable to send SMS, tune not valid. \n"
                    +currentTune.getTuneValidityStatus());
        }
        else{ // Okay, everything looks cool, ready to send the SMS
            showMessage(v, "Sending SMS..."); // Start of by showing message
            SendTune sendTune = new SendTune(currentTune); // Create sms class
            boolean success = sendTune.sendSMS(phoneNum); // Send the SMS!!
            if(success){ showMessage(v, "Message sent successfully");} // :)
            else{ showMessage(v, "Error sending message");} // :(
        }
    }


}
