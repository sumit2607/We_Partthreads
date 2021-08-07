    package com.example.we_part;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.progressindicator.CircularProgressIndicator;

public class MainActivity extends AppCompatActivity {

    private CircularProgressIndicator progressbar;
private TextView textView,textView1;

    private Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    int progress = (int) msg.obj;
                    progressbar.setProgress(progress);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(progress+" % loading");
                    break;
                case 2:
                    String message = (String) msg.obj;
                    progressbar.setVisibility(View.GONE);
                    textView.setVisibility(View.INVISIBLE);
                    textView1.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressbar = findViewById(R.id.progressbar);
        textView = findViewById(R.id.textview);
        textView1 = findViewById(R.id.textview1);
        WorkerThread workerThread = new WorkerThread("async", mainThreadHandler);
        workerThread.start();

    }


}