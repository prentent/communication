package com.lh.foractivity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lh.communication.bean.Book;
import com.lh.communication.bean.Book2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String stringExtra = getIntent().getStringExtra("data");
        TextView text = findViewById(R.id.text);
        text.setText(stringExtra == null ? "" : stringExtra);
        getTestInFile();
    }

    private void getTestInFile() {
        File file = null;
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Log.e("=====", "sk未挂载");
        } else {
            File directory = Environment.getExternalStorageDirectory();
            file = new File(directory, "ccc.txt");
        }
        if (file == null) return;
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
            Book2 book = (Book2) inputStream.readObject();
            Log.e("=======", book.getName() + "---" + book.getRice());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
