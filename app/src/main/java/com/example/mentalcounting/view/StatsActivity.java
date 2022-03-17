package com.example.mentalcounting.view;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.mentalcounting.service.StatsService;

public class StatsActivity extends AppCompatActivity {

    private StatsService statsService;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_stats);
        statsService = new StatsService(new StatsDao(new StatsBaseHelper(this)));
        editText = findViewById(R.id.StatsEditTextPersonalName);
        findViewById(R.id.StatsButtonReturn).setOnClickListener(view -> finish());
        findViewById(R.id.StatsButtonValidate).setOnClickListener(view -> editTextView(editText.getText().toString()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_validate,menu);
        MenuItem toolbarValidate = menu.findItem(R.id.toolbar_validateValidate);
        toolbarValidate.setOnMenuItemClickListener(menuItem -> editTextView(editText.getText().toString()));
        return true;
    }

    private boolean editTextView(String pn){
        TextView t1 = findViewById(R.id.StatsTextViewPersonalName);
        TextView t2 = findViewById(R.id.StatsTextViewLastScore);
        TextView t3 = findViewById(R.id.StatsTextViewBiggestScore);
        TextView t4 = findViewById(R.id.StatsTextViewSmallestScore);
        TextView t5 = findViewById(R.id.StatsTextViewAverageScore);
        TextView t6 = findViewById(R.id.StatsTextViewNumberGamesPlay);

        if(editText.getText().length() > 0 && statsService.getByName(pn).getPersonalName() != null) {

            t1.setText(getString(R.string.personal_name_DPoint) + " " + statsService.getByName(pn).getPersonalName());
            t2.setText(getString(R.string.last_score) + " " + statsService.getByName(pn).getLastScore());
            t3.setText(getString(R.string.biggestscore) + " " + statsService.getByName(pn).getBiggestScore());
            t4.setText(getString(R.string.smallest_score) + " " + statsService.getByName(pn).getSmallestScore());
            t5.setText(getString(R.string.average_score) + " " + statsService.getByName(pn).getAverage());
            t6.setText(getString(R.string.number_games_play) + " " + statsService.getByName(pn).getNumberGamesPlay());
        }

        return true;

    }


}