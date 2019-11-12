package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.core.app.ActivityCompat;
import android.widget.EditText;
import android.widget.TextView;

import android.app.Activity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class SMS extends AppCompatActivity {

    private static final int REQUEST_SEND_SMS = 1 ;
    Button sendBtn;
    Button returnBtn;
    EditText txtMessage;
    String phonenum = "No Number Entered";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
           phonenum = "Sending to " + extras.getString("KEY");
        }
        TextView myTextView = (TextView) findViewById(R.id.textView);
        myTextView.setText(phonenum);

        sendBtn = (Button) findViewById(R.id.buttonSMS1);
        returnBtn = (Button) findViewById(R.id.buttonCall1);
        txtMessage = (EditText) findViewById(R.id.editText);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String sms = txtMessage.getText().toString();
                if (ActivityCompat.checkSelfPermission(SMS.this,
                        Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SMS.this, new String[]{Manifest.permission.SEND_SMS},REQUEST_SEND_SMS);
                }
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phonenum, null, sms, null, null);
                    Toast.makeText(getApplicationContext(), "SMS Sent!",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(SMS.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

}
