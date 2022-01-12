package com.example.mentalcounting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class Calculator extends AppCompatActivity {


    private TextView textViewResCal;
    private TextView textViewOperation;

    private ArrayList<Button> listButtonNumerique = new ArrayList<Button>();

    //private Button buttonVirgule;

    private ArrayList<Button> listButtonOperation = new ArrayList<Button>();


    private Button buttonPlusMoins;
    private Button buttonAC;

    private ArrayList<Double> listNombres = new ArrayList<Double>();
    private int positionListeNombres = 0;
    private int operation = 0;
    private boolean autoCalcul = false;
    private boolean autoSupp = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        listNombres.add(0.0);
        listNombres.add(0.0);

        listButtonNumerique.add(findViewById(R.id.button_0));
        listButtonNumerique.add(findViewById(R.id.button_1));
        listButtonNumerique.add(findViewById(R.id.button_2));
        listButtonNumerique.add(findViewById(R.id.button_3));
        listButtonNumerique.add(findViewById(R.id.button_4));
        listButtonNumerique.add(findViewById(R.id.button_5));
        listButtonNumerique.add(findViewById(R.id.button_6));
        listButtonNumerique.add(findViewById(R.id.button_7));
        listButtonNumerique.add(findViewById(R.id.button_8));
        listButtonNumerique.add(findViewById(R.id.button_9));

        listButtonOperation.add(findViewById(R.id.button_diviser));
        listButtonOperation.add(findViewById(R.id.button_multiplier));
        listButtonOperation.add(findViewById(R.id.button_moins));
        listButtonOperation.add(findViewById(R.id.button_plus));
        listButtonOperation.add(findViewById(R.id.button_egale));
        listButtonNumerique.add(findViewById(R.id.button_modulo));

        buttonPlusMoins = findViewById(R.id.button_plusMoins);
        buttonAC = findViewById(R.id.button_AC);

        for (Button buttonNumerique:listButtonNumerique) {
            buttonNumerique.setOnClickListener(view -> addChiffre(Integer.parseInt(buttonNumerique.getText().toString())));
        }

        for (Button buttonOperation:listButtonOperation) {
            buttonOperation.setOnClickListener(view -> operation(buttonOperation.getText().toString()));
        }

        //buttonVirgule.setOnClickListener(view -> virgule());

        buttonPlusMoins.setOnClickListener(view -> plusMoins());
        buttonAC.setOnClickListener(view -> supprimer());

    }

    private void affichageTextView(){
        String afficher = listNombres.get(positionListeNombres).toString();

        if(afficher.substring(afficher.length()-2).equals(".0")){
            afficher = afficher.substring(0,afficher.length()-2);
        }
        textViewResCal = findViewById(R.id.textView_Resultat);
        textViewResCal.setText(afficher);
    }

    private void addChiffre(double chiffre){
        if(autoSupp){
            System.out.println(positionListeNombres);
            positionListeNombres--;
            listNombres.set(0, chiffre);
            System.out.println(positionListeNombres);
            autoCalcul = false;
            obligatoire("");
        }else{
            listNombres.set(positionListeNombres, listNombres.get(positionListeNombres) * 10 + chiffre);
        }
        if(positionListeNombres == 1)
            autoCalcul = true;
        affichageTextView();
    }

    /*
    private void virgule(){
        if(positionDecimal == 0) {
            positionDecimal++;
            decimalCalcul *= 10;
        }
        affichageTextView();
    }
    */
    private void operation(String synboleOperation){
        obligatoire(synboleOperation);
        switch(synboleOperation){
            case "÷" : operation = 1;
                break;
            case "×" : operation = 2;
                break;
            case "-" : operation = 3;
                break;
            case "+" : operation = 4;
                break;
            case "%" : operation = 5;
                break;
        }
        autoSupp = false;
    }
    private void diviser(){
        obligatoire("÷");
        operation = 1;

    }
    private void multiplier(){
        obligatoire("×");
        operation = 2;
    }
    private void moins(){
        obligatoire("-");
        operation = 3;
    }
    private void plus(){
        obligatoire("+");
        operation = 4;
    }
    private void egale(){
        for (double nombre:listNombres) {
            System.out.println(nombre);
        }
        switch (operation){
            case 0: affichageTextView();
                break;
            case 1: listNombres.set(positionListeNombres-1,listNombres.get(positionListeNombres-1)/listNombres.get(positionListeNombres));
                listNombres.set(positionListeNombres,0.0);
                positionListeNombres--;
                break;
            case 2: listNombres.set(positionListeNombres-1,listNombres.get(positionListeNombres-1)*listNombres.get(positionListeNombres));
                listNombres.set(positionListeNombres,0.0);
                positionListeNombres--;
                break;
            case 3: listNombres.set(positionListeNombres-1,listNombres.get(positionListeNombres-1)-listNombres.get(positionListeNombres));
                listNombres.set(positionListeNombres,0.0);
                positionListeNombres--;
                break;
            case 4: listNombres.set(positionListeNombres-1,listNombres.get(positionListeNombres-1)+listNombres.get(positionListeNombres));
                listNombres.set(positionListeNombres,0.0);
                positionListeNombres--;
                break;
        }
        autoSupp = true;
        operation = 0;
        textViewOperation = findViewById(R.id.textView_Operation);
        textViewOperation.setText("");
        affichageTextView();
    }

    private void diviserParCent(){
        listNombres.set(positionListeNombres, listNombres.get(positionListeNombres)/100);
        affichageTextView();
    }
    private void plusMoins(){
        listNombres.set(positionListeNombres,listNombres.get(positionListeNombres)*-1);
        affichageTextView();
    }
    private void supprimer(){
        listNombres.set(positionListeNombres,listNombres.get(positionListeNombres)*0);
        affichageTextView();
    }

    private void obligatoire(String operation){
        if(autoCalcul)
            egale();
        if (positionListeNombres == 0)
            positionListeNombres++;
        textViewOperation = findViewById(R.id.textView_Operation);
        textViewOperation.setText(operation);
    }
}