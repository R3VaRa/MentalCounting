package com.example.mentalcounting.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.mentalcounting.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_main);

        findViewById(R.id.MainButtonMentalCounting).setOnClickListener(view -> lunchMentalCounting());
        findViewById(R.id.MainButtonStats).setOnClickListener(view -> lunchStatistics());
        findViewById(R.id.MainButtonTop).setOnClickListener(view -> {startActivity(new Intent(this, TopTenActivity.class));});
        findViewById(R.id.MainButtonSource).setOnClickListener(view -> {startActivity(new Intent(this, SourceActivity.class));});
    }

    private void lunchMentalCounting() {
        Intent i = new Intent(this, MentalCountingActivity.class);
        startActivity(i);
    }

    private void lunchStatistics() {
        startActivity(new Intent(this, StatsActivity.class));
    }

}