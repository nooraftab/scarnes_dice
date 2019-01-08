package com.example.demouser.dicey;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //score variables for user and computer
    private int userOverallScore;
    private int userTurnScore;
    private int computerOverallScore;
    private int computerTurnScore;

    private ImageView dice;
    private Button rollButton;
    private Button holdButton;
    private Button resetButton;

    private TextView extraScore;
    private TextView yourScore;
    private TextView comScore;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yourScore = findViewById(R.id.yourScore);
        comScore = findViewById(R.id.comScore);
        extraScore = findViewById(R.id.extra);

        dice = findViewById(R.id.oneDice);

        rollButton = findViewById(R.id.roll);
        holdButton = findViewById(R.id.hold);
        resetButton = findViewById(R.id.insert);

        //userTurnScore = 0;

        rollButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                doARoll();

            }

        });

        resetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                userOverallScore = 0;
                userTurnScore = 0;
                computerOverallScore = 0;
                computerTurnScore= 0;

                yourScore.setText("Your score = " + 0);
                comScore.setText("Computer score = " + 0);
                extraScore.setText("Play a turn");

            }
        });

        holdButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                userOverallScore = userOverallScore + userTurnScore;
                yourScore.setText("Your Score = " + userOverallScore);
                userTurnScore = 0;
                extraScore.setText("Your Turn Score = " + userTurnScore);

            }

        });

    }



    private void doARoll() {

        int diceValue = random.nextInt(6) + 1;

        //could've put dice in an array

        switch(diceValue) {
            case 1:
                dice.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice1, null));
                userTurnScore = 0;
                extraScore.setText("Your Turn Score = " + 0);
                break;

            case 2:
                dice.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice2, null));
                userTurnScore += diceValue;
                extraScore.setText("Your Turn Score = " + userTurnScore);
                break;

            case 3:
                dice.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice3, null));
                userTurnScore += diceValue;
                extraScore.setText("Your Turn Score = " + userTurnScore);
                break;

            case 4:
                dice.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice4, null));
                userTurnScore += diceValue;
                extraScore.setText("Your Turn Score = " + userTurnScore);
                break;

            case 5:
                dice.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice5, null));
                userTurnScore += diceValue;
                extraScore.setText("Your Turn Score = " + userTurnScore);
                break;
            case 6:
                dice.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice6, null));
                userTurnScore += diceValue;
                extraScore.setText("Your Turn Score = " + userTurnScore);
                break;


        }

    }


}
