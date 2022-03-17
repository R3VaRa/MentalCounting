package com.example.mentalcounting.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mentalcounting.R;
import com.example.mentalcounting.database.StatsBaseHelper;
import com.example.mentalcounting.database.StatsDao;
import com.example.mentalcounting.entity.Stats;
import com.example.mentalcounting.service.StatsService;

public class RegisterActivity extends AppCompatActivity {

    private StatsService statsService;
    private Integer score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_register);
        statsService = new StatsService(new StatsDao(new StatsBaseHelper(this)));

        score = getIntent().getIntExtra("score",0);
        editTextViewScore();

        findViewById(R.id.button).setOnClickListener(view -> lunch());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_validate,menu);
        MenuItem toolbarValidate = menu.findItem(R.id.toolbar_validateValidate);
        toolbarValidate.setOnMenuItemClickListener(menuItem -> lunch());
        return true;
    }

    @SuppressLint("SetTextI18n")
    private void editTextViewScore(){
        TextView RegisterTextViewScoreIs = findViewById(R.id.RegisterTextViewScoreIs);
        RegisterTextViewScoreIs.setText(RegisterTextViewScoreIs.getText() + " " + score.toString());
    }

    private boolean lunch() {
        EditText text = findViewById(R.id.RegisterEditTextPersonalName);
        if(text.getText().length() > 0){
            Stats stats = new Stats();
            stats.setPersonalName(text.getText().toString());
            stats.setScore(score);
            statsService.storeStatsInDatabase(stats);
            finish();
            return true;
        }
        return false;
    }
}