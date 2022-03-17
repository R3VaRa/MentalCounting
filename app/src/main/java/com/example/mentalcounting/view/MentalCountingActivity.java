package com.example.mentalcounting.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mentalcounting.Calculate;
import com.example.mentalcounting.R;

public class MentalCountingActivity extends AppCompatActivity {

    private Integer input = 0;
    // 963*2 c'est une valeur que j'ai décité :-)
    private final Integer HIGH_STATION = 963*2;
    private TextView MentalCountingTextViewInput;
    private TextView MentalCountingTextViewCalculate;
    private TextView MentalCountingTextViewValidate;
    private TextView MentalCountingTextViewLives;
    private TextView MentalCountingTextViewScore;
    private Calculate calculate;
    private Integer lives = 3;
    private Integer score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_mental_counting);
        findViewById(R.id.MentalCountingButton0).setOnClickListener(view -> addValue(0));
        findViewById(R.id.MentalCountingButton1).setOnClickListener(view -> addValue(1));
        findViewById(R.id.MentalCountingButton2).setOnClickListener(view -> addValue(2));
        findViewById(R.id.MentalCountingButton3).setOnClickListener(view -> addValue(3));
        findViewById(R.id.MentalCountingButton4).setOnClickListener(view -> addValue(4));
        findViewById(R.id.MentalCountingButton5).setOnClickListener(view -> addValue(5));
        findViewById(R.id.MentalCountingButton6).setOnClickListener(view -> addValue(6));
        findViewById(R.id.MentalCountingButton7).setOnClickListener(view -> addValue(7));
        findViewById(R.id.MentalCountingButton8).setOnClickListener(view -> addValue(8));
        findViewById(R.id.MentalCountingButton9).setOnClickListener(view -> addValue(9));

        findViewById(R.id.MentalCountingButtonClear).setOnClickListener(view ->clearValue());
        findViewById(R.id.MentalCountingButtonValidation).setOnClickListener(view -> validation());

        MentalCountingTextViewInput = findViewById(R.id.MentalCountingTextViewInput);
        MentalCountingTextViewCalculate = findViewById(R.id.MentalCountingTextViewCalculate);
        MentalCountingTextViewValidate = findViewById(R.id.MentalCountingTextViewValidate);
        MentalCountingTextViewLives = findViewById(R.id.MentalCountingTextViewLives);
        MentalCountingTextViewScore = findViewById(R.id.MentalCountingTextViewScore);

        calculate = new Calculate();
        updateTextViewCalculate();
        updateTextViewScore();
        updateTextViewInput();
    }

    @SuppressLint("SetTextI18n")
    private void updateTextViewInput() {
        MentalCountingTextViewInput.setText(input.toString());
    }

    @SuppressLint("SetTextI18n")
    private void updateTextViewCalculate() {
        MentalCountingTextViewCalculate.setText(calculate.getFirstItem() + " " + calculate.getSymbolTypeOperation() + " " + calculate.getSecondItem());
    }

    @SuppressLint("SetTextI18n")
    private void updateTextViewScore() {
        MentalCountingTextViewScore.setText(getString(R.string.score) + " " + score.toString());
    }

    private void updateTextView_C_or_In() {
        MentalCountingTextViewValidate.setText(getString(R.string.correct_or_incorrect_answer));
    }

    private void addValue(Integer value) {
        if (10 * input + value > HIGH_STATION) {
            Toast.makeText(this, getString(R.string.ERROR_TOO_LARGE), Toast.LENGTH_LONG).show();
        } else {
            input = 10 * input + value;
        }
        updateTextViewInput();
        updateTextView_C_or_In();
    }

    private void clearValue(){
        input = 0;
        updateTextViewInput();
    }

    private void validation(){
        // Lancer la vérification
        if(calculate.verification(input.doubleValue())){
            MentalCountingTextViewValidate.setText(getString(R.string.correct_answer));
            score++;
        }else {
            MentalCountingTextViewValidate.setText(getString(R.string.incorrect_answer));
            switch (lives){
                case 3:
                    lives--;
                    MentalCountingTextViewLives.setText(getString(R.string.lives_2));
                    break;
                case 2:
                    lives--;
                    MentalCountingTextViewLives.setText(getString(R.string.life_1));
                    break;
                case 1:
                    lives = 3;
                    lunchRegister();
                    finish();
                    break;
            }
        }
        clearValue();
        calculate.randomAll();
        updateTextViewCalculate();
        updateTextViewScore();
    }

    private void lunchRegister(){
        startActivity(new Intent(this, RegisterActivity.class).putExtra("score", score));
    }
}