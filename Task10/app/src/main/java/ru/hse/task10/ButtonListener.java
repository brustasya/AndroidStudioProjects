package ru.hse.task10;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(R.string.alertMessage)
                .setCancelable(false)

                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.finish();
                    }
                })

                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private final AppCompatActivity activity;

    public ButtonListener(AppCompatActivity activity) {
        this.activity = activity;
    }
}
