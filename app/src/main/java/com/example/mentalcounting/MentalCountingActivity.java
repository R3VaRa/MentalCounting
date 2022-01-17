package com.example.mentalcounting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MentalCountingActivity extends AppCompatActivity {

    private TextView TxV_Mental_Counting;
    private TextView TxV_Saisie;
    private ArrayList<Double> listNombre = new ArrayList<Double>();
    private ArrayList<Button> listButton = new ArrayList<Button>();
    private Button buttonPlusMoin;
    private Button buttonValider;
    private int operation;
    private String nombreSaisie = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental_counting);

        TxV_Saisie = findViewById(R.id.textView_Saisie);
        listButton.add(findViewById(R.id.button_0));
        listButton.add(findViewById(R.id.button_1));
        listButton.add(findViewById(R.id.button_2));
        listButton.add(findViewById(R.id.button_3));
        listButton.add(findViewById(R.id.button_4));
        listButton.add(findViewById(R.id.button_5));
        listButton.add(findViewById(R.id.button_6));
        listButton.add(findViewById(R.id.button_7));
        listButton.add(findViewById(R.id.button_8));
        listButton.add(findViewById(R.id.button_9));
        listButton.add(findViewById(R.id.button_virgul));
        //listButton.add(findViewById(R.id.button_plusMoins));
        buttonPlusMoin = findViewById(R.id.button_plusMoins);

        buttonValider = findViewById(R.id.validation);

        buttonValider.setOnClickListener(view -> affichageTxValisation());

        for (Button button:listButton) {
            button.setOnClickListener(view -> saisieChiffre(button.getText().toString()));
        }
        buttonPlusMoin.setOnClickListener(view -> plusMoins());
        ConcatenCalcul();
    }


    private void affichageTextView(String txt) {
        TxV_Mental_Counting = findViewById(R.id.textView_Mental_Counting);
        TxV_Mental_Counting.setText(txt);
    }

    private void plusMoins(){
        if(nombreSaisie.isEmpty()){
            nombreSaisie = "-";
        }
        else if(nombreSaisie.charAt(0) == '-'){
            nombreSaisie = nombreSaisie.substring(1);
        }else {
            nombreSaisie = "-" + nombreSaisie;
        }
        TxV_Saisie.setText(nombreSaisie);
    }

    private void affichageTxValisation(){
        TxV_Saisie = findViewById(R.id.textView_Saisie);
        if(isValidate()){
            nombreSaisie = "Validate";
        }else {
            nombreSaisie = "not Validate";
        }
        TxV_Saisie.setText(nombreSaisie);
        ConcatenCalcul();
    }

    private Boolean isValidate(){
        return nombreSaisie.equals(listNombre.get(2).toString().substring(0,listNombre.get(2).toString().length()-2));
    }

    private void saisieChiffre(String chiffre){
        nombreSaisie = nombreSaisie + chiffre;
        TxV_Saisie = findViewById(R.id.textView_Saisie);
        TxV_Saisie.setText(nombreSaisie);
    }

    private void ConcatenCalcul() {
        nombreSaisie = "";
        listNombre.clear();
        listNombre.add((double) randomNombre(10, 100));
        listNombre.add((double) randomNombre(10, 100));
        operation = randomOperation();
        switch (operation) {
            case 0:
                listNombre.add(listNombre.get(0) + listNombre.get(1));
                affichageTextView(listNombre.get(0).toString().substring(0, listNombre.get(0).toString().length() - 2) + " + " + listNombre.get(1).toString().substring(0, listNombre.get(1).toString().length() - 2));
                break;
            case 1:
                listNombre.add(listNombre.get(0) - listNombre.get(1));
                affichageTextView(listNombre.get(0).toString().substring(0, listNombre.get(0).toString().length() - 2) + " - " + listNombre.get(1).toString().substring(0, listNombre.get(1).toString().length() - 2));
                break;
            case 2:
                listNombre.add(listNombre.get(0) * listNombre.get(1));
                affichageTextView(listNombre.get(0).toString().substring(0, listNombre.get(0).toString().length() - 2) + " × " + listNombre.get(1).toString().substring(0, listNombre.get(1).toString().length() - 2));
                break;
            case 3:
                listNombre.add(listNombre.get(0) / listNombre.get(1));
                affichageTextView(listNombre.get(0).toString().substring(0, listNombre.get(0).toString().length() - 2) + " ÷ " + listNombre.get(1).toString().substring(0, listNombre.get(1).toString().length() - 2));
                break;
        }
    }

    private int randomNombre(int indexDeb, int indexFin) {
        return indexDeb + new Random().nextInt(indexFin - indexDeb);
    }

    private int randomOperation() {
        return new Random().nextInt(3);
    }
}