package com.example.mentalcounting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CalculatriceTP2 extends AppCompatActivity {

    private TextView textViewCalculResultat;
    private String affichageDuCalcul = "";

    private boolean chiffrePasOperation = true;
    private boolean pasDejaEgale = true;

    private ArrayList<Button> buttonArrayList = new ArrayList<Button>();
    private ArrayList<String> chiffreArrayList = new ArrayList<String>();
    private ArrayList<String> operationArrayList = new ArrayList<String>();
    private ArrayList<Integer> chiffreCalcul = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculatrice_tp2);

        textViewCalculResultat = findViewById(R.id.textView_CalculResultat);

        buttonArrayList.add(findViewById(R.id.button_0));
        buttonArrayList.add(findViewById(R.id.button_1));
        buttonArrayList.add(findViewById(R.id.button_2));
        buttonArrayList.add(findViewById(R.id.button_3));
        buttonArrayList.add(findViewById(R.id.button_4));
        buttonArrayList.add(findViewById(R.id.button_5));
        buttonArrayList.add(findViewById(R.id.button_6));
        buttonArrayList.add(findViewById(R.id.button_7));
        buttonArrayList.add(findViewById(R.id.button_8));
        buttonArrayList.add(findViewById(R.id.button_9));

        for (int i = 0; i < 10; i++) {
            chiffreArrayList.add(i + "");
        }
        operationArrayList.add("+");
        operationArrayList.add("-");
        operationArrayList.add("÷");
        operationArrayList.add("×");

        buttonArrayList.add(findViewById(R.id.button_plus));
        buttonArrayList.add(findViewById(R.id.button_moins));
        buttonArrayList.add(findViewById(R.id.button_multiplier));
        buttonArrayList.add(findViewById(R.id.button_diviser));


        for (Button button : buttonArrayList) {
            button.setOnClickListener(view -> texteAdd(button.getText().toString()));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_calculatrice_tp2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.compute:
                compute();
                return true;
            case R.id.clear:
                return true;
            case R.id.home:
                returnToHome();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void compute() {
        calculer();
        System.out.println("COMPUTE");
    }

    private void returnToHome() {
        Intent intent = new Intent(CalculatriceTP2.this, MainActivity.class);
        startActivity(intent);
    }

    private void addToCalcul(int calcul) {
        chiffreCalcul.add(calcul);
    }

    private void calculer() {
        if (pasDejaEgale && chiffreCalcul.size() >= 2) {
            double resultat = 0;
            switch (affichageDuCalcul.substring(2, 3)) {
                case "+":
                    resultat = chiffreCalcul.get(0) + chiffreCalcul.get(1);
                    break;
                case "-":
                    resultat = chiffreCalcul.get(0) - chiffreCalcul.get(1);
                    break;
                case "÷":
                    resultat = Double.parseDouble(chiffreCalcul.get(0).toString()) / Double.parseDouble(chiffreCalcul.get(1).toString());
                    break;
                case "×":
                    resultat = chiffreCalcul.get(0) * chiffreCalcul.get(1);
                    break;
                default:
                    resultat = 987654321;
                    break;
            }
            affichageDuCalcul = affichageDuCalcul + " = " + resultat;
            if (affichageDuCalcul.substring(affichageDuCalcul.length() - 2).equals(".0")) {
                affichageDuCalcul = affichageDuCalcul.substring(0, affichageDuCalcul.length() - 2);
            }
            textViewCalculResultat.setText(affichageDuCalcul);
            pasDejaEgale = false;
        }
    }

    private void texteAdd(String caract) {
        apresEgale();
        if (chiffreCalcul.size() < 2) {
            if (chiffrePasOperation) {
                for (String chiffreAL : chiffreArrayList) {
                    if (caract.equals(chiffreAL)) {
                        affichageDuCalcul = affichageDuCalcul + caract + " ";
                        textViewCalculResultat.setText(affichageDuCalcul);
                        addToCalcul(Integer.parseInt(caract));
                        chiffrePasOperation = false;
                    }
                }
            } else {
                for (String operationAL : operationArrayList) {
                    if (caract.equals(operationAL)) {
                        affichageDuCalcul = affichageDuCalcul + caract + " ";
                        textViewCalculResultat.setText(affichageDuCalcul);
                        chiffrePasOperation = true;
                    }
                }
            }
        }
    }

    private void apresEgale(){
        if (!pasDejaEgale) {
            affichageDuCalcul = "";
            chiffreCalcul.clear();
            chiffrePasOperation = true;
            pasDejaEgale = true;
        }
    }

}