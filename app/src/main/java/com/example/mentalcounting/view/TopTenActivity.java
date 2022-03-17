package com.example.mentalcounting.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mentalcounting.R;
import com.example.mentalcounting.database.StatsBaseHelper;
import com.example.mentalcounting.database.StatsDao;
import com.example.mentalcounting.entity.Stats;
import com.example.mentalcounting.service.StatsService;

import java.util.List;

public class TopTenActivity extends AppCompatActivity {

    TextView[] topTextviewAll;
    List<Stats> statsTopTen;
    StatsService statsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_top_ten);
        topTextviewAll = new TextView[10];
        topTextviewAll[0] = findViewById(R.id.TopTextView1);
        topTextviewAll[1] = findViewById(R.id.TopTextView2);
        topTextviewAll[2] = findViewById(R.id.TopTextView3);
        topTextviewAll[3] = findViewById(R.id.TopTextView4);
        topTextviewAll[4] = findViewById(R.id.TopTextView5);
        topTextviewAll[5] = findViewById(R.id.TopTextView6);
        topTextviewAll[6] = findViewById(R.id.TopTextView7);
        topTextviewAll[7] = findViewById(R.id.TopTextView8);
        topTextviewAll[8] = findViewById(R.id.TopTextView9);
        topTextviewAll[9] = findViewById(R.id.TopTextView10);

        statsService = new StatsService(new StatsDao(new StatsBaseHelper(this)));

        statsTopTen = statsService.getSortByScore();

        update();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_return,menu);
        MenuItem toolbarValidate = menu.findItem(R.id.toolbar_returnReturn);
        toolbarValidate.setOnMenuItemClickListener(menuItem -> isfinish());
        return true;
    }

    private boolean isfinish(){
        finish();
        return true;
    }

    private void update() {
        if (statsTopTen.size() > 10)
            for (int i = 0; i < 10; i++)
                topTextviewAll[i].setText("Top " + (i + 1) + " " + statsTopTen.get(i).toStringName() + getString(R.string.score) + " " + statsTopTen.get(i).toStringScore());
        else {
            for (int i = 0; i < statsTopTen.size(); i++)
                topTextviewAll[i].setText("Top " + (i + 1) + " " + statsTopTen.get(i).toStringName() + getString(R.string.score) + " " + statsTopTen.get(i).toStringScore());
            for (int i = statsTopTen.size(); i < 10; i++)
                topTextviewAll[i].setText("");
        }
    }
}