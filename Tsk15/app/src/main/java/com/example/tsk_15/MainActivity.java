package com.example.tsk_15;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    private EditText edit1;
    Integer i;
    String[] from;
    int[] to;
    static ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        from = new String[]{"Name"};
        to = new int[]{R.id.textView};
        Button btnadd = findViewById(R.id.button);
        final EditText editadd =
                findViewById(R.id.editText);
        SQLiteDatabase db =
                openOrCreateDatabase("DBName", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MyTable5 (_id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR);");
        Cursor cursor = db.rawQuery("SELECT * FROM Mytable5", null);
        i = cursor.getCount() + 1;

        if (cursor.getCount() > 0) {
            MyCursorAdapter scAdapter = new
                    MyCursorAdapter(MainActivity.this, R.layout.list_item, cursor, from, to);
            listView = getListView();
            listView.setAdapter(scAdapter);
        }
        db.close();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db =
                        openOrCreateDatabase("DBName", MODE_PRIVATE, null);
                Cursor cursor2 = db.rawQuery("SELECT * FROM Mytable5", null);
                i = cursor2.getCount() + 1;

                for (int k = 1; k <= i; k++) {
                    Cursor cursor3 = db.rawQuery("SELECT * FROM Mytable5 WHERE _id=" + k + "", null);
                    if (cursor3.getCount() == 0) {
                        i = k;
                        break;
                    }
                }

                db.execSQL("INSERT INTO MyTable5 VALUES ('" + i + "','" + editadd.getText().toString() + "');");
                Cursor cursor = db.rawQuery("SELECT * FROM Mytable5", null);
                MyCursorAdapter scAdapter = new MyCursorAdapter(MainActivity.this, R.layout.list_item, cursor, from
                        , to);
                listView = getListView();
                listView.setAdapter(scAdapter);
                db.close();
                Toast.makeText(getListView().getContext(), "a row added to the table", Toast.LENGTH_LONG).show();
            }
        });

        edit1 = findViewById(R.id.editText);
        SharedPreferences save = getSharedPreferences("SAVE", 0);
        edit1.setText(save.getString("text", ""));


    }

    @Override
    protected void onStop() {
        SharedPreferences save = getSharedPreferences("SAVE", 0);
        SharedPreferences.Editor editor = save.edit();
        editor.putString("text", edit1.getText().toString());
        editor.commit();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.inf) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            try {
                dialog.setMessage(getTitle().toString() + " версия " +
                        getPackageManager().getPackageInfo(getPackageName(), 0).versionName +
                        "\r\n\nЛабораторная работа №15 \r\n\n Автор: Бобрускина Станислава Алексеевна, БПИ207");
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            dialog.setTitle("О программе")
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(R.mipmap.ic_launcher_round);

            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}