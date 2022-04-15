package com.example.task22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int entries = 6;
    private String phoneNum[];
    private String buttonLabels[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNum = new String[entries];
        buttonLabels = new String[entries];

        populateArrays();

        TextView textView1 = (TextView)findViewById(R.id.textView1); textView1.setText(buttonLabels[0]);


        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);

    }

    public void launchDialer(String number){
        String numberToDial = "tel:"+number;
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(numberToDial)));
    }

    public void populateArrays(){
        phoneNum[0] = "123-456-78-90";
        phoneNum[1] = "234-567-89-01";
        phoneNum[2] = "345-678-90-12";
        phoneNum[3] = "456-789-01-23";
        phoneNum[4] = "567-890-12-34";
        phoneNum[5] = "678-901-23-45";
        buttonLabels[0] = "Иванов Ваня";
        buttonLabels[1] = "Петров Петя";
        buttonLabels[2] = "Семеныч Сеня";
        buttonLabels[3] = "Кузнецова Катя";
        buttonLabels[4] = "Смирнова Саша";
        buttonLabels[5] = "Попова Полина";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                launchDialer(phoneNum[0]);
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNum[0], null, null, null, null);
                break;
            case R.id.button2:
                launchDialer(phoneNum[1]);
                break;

        }
    }
}