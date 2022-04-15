package ru.whiteroomlz.task21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    int currentImage = 0;
    ArrayList<String> images;
    ImageView imageView;
    TextView nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button previousButton = findViewById(R.id.previousButton);
        previousButton.setOnClickListener(this::onPrevious);

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this::onNext);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(GalleryActivity.this);
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
    public void onResume() {
        super.onResume();
        currentImage = 0;
        Log.d("myLogs", "onResume cI=" + currentImage);
        nameView = ((TextView) findViewById(R.id.imageName));
        images = new ArrayList<String>();
        imageView = ((ImageView) findViewById(R.id.image));
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);
        }
        try {
            File imagesDirectory = new File(Environment.getExternalStorageDirectory().toString()+
                    "/TrainingMedia");
            images = searchImage(imagesDirectory);
            updatePhoto(Uri.parse(images.get(currentImage)));
        } catch (Exception e) {
            nameView.setText("Ошибка: Папка '/TrainingMedia/' не найдена");
            Log.d("myLogs", "Ошибка");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        images.clear();
        Log.d("myLogs", "onPause cI=" + currentImage);
    }

    private ArrayList<String> searchImage(File dir) {
        ArrayList<String> imagesFinded = new ArrayList<String>();
        for (File f : dir.listFiles()) {
            if (!f.isDirectory()) {
                String fileExt = getFileExt(f.getAbsolutePath());
                if (fileExt.equals("png") || fileExt.equals("jpg") || fileExt.equals("jpeg")) {
                    Log.d("myLogs", "Файл найден " + f.getAbsolutePath());
                    imagesFinded.add(f.getAbsolutePath());
                }
            }
        }
        return imagesFinded;
    }

    public static String getFileExt(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    public void updatePhoto(Uri uri) {
        try {
            nameView.setText((currentImage + 1) + "/" + images.size());
            imageView.setImageURI(uri);
        } catch (Exception e) {
            nameView.setText("Ошибка загрузки файла");
        }
    }

    public void onNext(View v) {
        if (currentImage + 1 < images.size() && images.size() > 0) {
            currentImage++;
            updatePhoto(Uri.parse(images.get(currentImage)));
        }
    }

    public void onPrevious(View v) {
        if (currentImage > 0 && images.size() > 0) {
            currentImage--;
            updatePhoto(Uri.parse(images.get(currentImage)));
        }
    }
}
