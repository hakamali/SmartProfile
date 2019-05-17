package com.example.meefaisal.smartprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class splashActivity extends AppCompatActivity {
    private long splash = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TimerTask tast = new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intent);
                splashActivity.this.finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(tast,splash);
    }
}
