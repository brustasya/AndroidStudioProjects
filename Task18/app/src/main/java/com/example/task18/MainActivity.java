package com.example.task18;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GestureLibrary gLib;
    GestureOverlayView gestures;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gLib.load()) {
            finish();
        }
        tvOutput = findViewById(R.id.textView);
        gestures = findViewById(R.id.gestureOverlayView1);
        gestures.addOnGesturePerformedListener(this::onGesturePerformed);
    }

    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        //Создаёт ArrayList c загруженными из gestures жестами
        ArrayList<Prediction> predictions = gLib.recognize(gesture);
        if (predictions.size() > 0) {
            //если загружен хотябы один жест из gestures
            Prediction prediction = predictions.get(0);
            if (prediction.score > 1.0) {
                if (prediction.name.equals("A")) {
                    tvOutput.setText("S");
                } else if (prediction.name.equals("B")) {
                    tvOutput.setText("B");
                } else if (prediction.name.equals("C")) {
                    tvOutput.setText("С");
                }
            } else {
                tvOutput.setText("Жест неизвестен");
            }
        }
    }

}