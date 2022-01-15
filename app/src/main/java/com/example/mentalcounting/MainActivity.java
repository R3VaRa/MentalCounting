package com.example.mentalcounting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.time.Instant;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Button> listButtonList = new ArrayList<Button>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listButtonList.add(findViewById(R.id.buttonCalculator));
        listButtonList.add(findViewById(R.id.buttonMentalActivity));
        listButtonList.add(findViewById(R.id.buttonCalculatriceTP2));
        for (Button lbl:listButtonList) {
            lbl.setOnClickListener(view -> changerFenetre(lbl.getText().toString()));
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_compute, menu);
//        return true;
//    }

    private void changerFenetre(String txt){
        if(txt.equals("Calculator")){
            Intent intent = new Intent(MainActivity.this,Calculator.class);
            startActivity(intent);
        }else if(txt.equals("MentalActivity")){
            Intent intent = new Intent(MainActivity.this,MentalCountingActivity.class);
            startActivity(intent);
        }else if(txt.equals("CalculatriceTP2")){
            Intent intent = new Intent(MainActivity.this,CalculatriceTP2.class);
            startActivity(intent);
        }
    }
}