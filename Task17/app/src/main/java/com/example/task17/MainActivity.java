package com.example.task17;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends Activity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    TextView tvOutput;
    GestureDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOutput = findViewById(R.id.textView5);
        mDetector = new GestureDetector(this, this);
        mDetector.setOnDoubleTapListener(this);
    }

    public boolean onTouchEvent(MotionEvent e) {
        this.mDetector.onTouchEvent(e);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        tvOutput.setText("onDown: " + e.toString());
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        tvOutput.setText("onFling: " + e1.toString() + e2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        tvOutput.setText("onLongPress: " + e.toString());
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        tvOutput.setText("onScroll: " + e1.toString() + e2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        tvOutput.setText("onShowPress: " + e.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        tvOutput.setText("onSingleTapUp: " + e.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        tvOutput.setText("onDoubleTap: " + e.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        tvOutput.setText("onDoubleTapEvent: " + e.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        tvOutput.setText("onSingleTapConfirmed: " + e.toString());
        return true;
    }

}