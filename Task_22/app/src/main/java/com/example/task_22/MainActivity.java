package com.example.task_22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements OnClickListener
{

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

        Button button1 = (Button)findViewById(R.id.button1); button1.setText(buttonLabels[0]);
        button1.setOnClickListener(this);
        Button button2 = (Button)findViewById(R.id.button2); button2.setText(buttonLabels[1]);
        button2.setOnClickListener(this);
        Button button3 = (Button)findViewById(R.id.button3); button3.setText(buttonLabels[2]);
        button3.setOnClickListener(this);
        Button button4 = (Button)findViewById(R.id.button4); button4.setText(buttonLabels[3]);
        button4.setOnClickListener(this);
        Button button5 = (Button)findViewById(R.id.button5); button5.setText(buttonLabels[4]);
        button5.setOnClickListener(this);
        Button button6 = (Button)findViewById(R.id.button6); button6.setText(buttonLabels[5]);
        button6.setOnClickListener(this);

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

    public void launchDialer(String number){
        String numberToDial = "tel:"+number;
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(numberToDial)));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        try {
            dialog.setMessage(getTitle().toString() + " версия " +
                    getPackageManager().getPackageInfo(getPackageName(), 0).versionName +
                    "\r\n\nАвтор: Бобрускина Станислава, БПИ207");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        dialog.setTitle("О программе")
                .setNeutralButton("OK", (dialog1, which) -> dialog1.dismiss())
                .setIcon(R.mipmap.ic_launcher_round);

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                launchDialer(phoneNum[0]);
                break;
            case R.id.button2:
                launchDialer(phoneNum[1]);
                break;
            case R.id.button3:
                launchDialer(phoneNum[2]);
                break;
            case R.id.button4:
                launchDialer(phoneNum[3]);
                break;
            case R.id.button5:
                launchDialer(phoneNum[4]);
                break;
            case R.id.button6:
                launchDialer(phoneNum[5]);
                break;
        }
    }
}