package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.core.app.ActivityCompat;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button sms;
    private EditText edittext;
    private static final int REQUEST_PHONE_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.buttonCall);
        sms = (Button) findViewById(R.id.buttonSMS);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                edittext = (EditText) findViewById(R.id.editText3);
                callIntent.setData(Uri.parse("tel:" + edittext.getText().toString()));

                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                }
                startActivity(callIntent);
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, SMS.class);
                edittext = (EditText) findViewById(R.id.editText3);
                i.putExtra("KEY",edittext.getText().toString());
                startActivity(i);
            }
        });

    }
}
