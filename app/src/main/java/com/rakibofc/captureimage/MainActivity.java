package com.rakibofc.captureimage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        int requestCode = 1111;

        Intent intent = new Intent(getApplicationContext(), Alarm.class);
        @SuppressLint("InlinedApi") PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), requestCode, intent,PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        button.setOnClickListener(v -> {

            try {
                if (alarmManager!= null) {
                    alarmManager.cancel(pendingIntent);
                    pendingIntent.cancel();
                    Toast.makeText(MainActivity.this, "Canceled" + requestCode, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Canceled" + requestCode, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Canceled" + requestCode, Toast.LENGTH_SHORT).show();
            }
        });

        startService(new Intent(getApplicationContext(), MyService.class));
    }
}