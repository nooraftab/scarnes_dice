package com.example.demouser.dicey;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demouser.dicey.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    //score variables for user and computer
    private int userOverallScore;
    private int userTurnScore;
    private int computerOverallScore;
    private int computerTurnScore;
    private int diceValue;

    private ImageView dice;
    private Button rollButton;
    private Button holdButton;
    private Button resetButton;

    private TextView extraScore;
    private TextView yourScore;
    private TextView comScore;

    private boolean computerTurn = true;

    private Random random = new Random();

    private Handler handler = new Handler();
    private Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize UI components in main code
        yourScore = findViewById(R.id.yourScore);   //textviews
        comScore = findViewById(R.id.comScore);
        extraScore = findViewById(R.id.extra);

        dice = findViewById(R.id.oneDice);  //images

        rollButton = findViewById(R.id.roll);   //buttons
        holdButton = findViewById(R.id.hold);
        resetButton = findViewById(R.id.reset);

        extraScore.setText("User plays first! Press roll to start :)");

        //initialize action listeners & define functionality for buttons
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollButton.setEnabled(true);
                holdButton.setEnabled(true);

                doARoll();
                //if 1
                if (diceValue==1) {
                    //reset turn score and reset text label
                    userTurnScore = 0;
                    extraScore.setText("Your Turn Score = " + userTurnScore);
                    userOverallScore += userTurnScore;
                    yourScore.setText("Your Score = " + userOverallScore);

                    //computer's turn
                    computerTurn();

                }
                else{
                    userTurnScore += diceValue; //update the turn score
                    extraScore.setText("Your Turn Score = " + userTurnScore);   //update the label
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //reset all scores to 0
                userOverallScore = 0;
                userTurnScore = 0;
                computerOverallScore = 0;
                computerTurnScore= 0;
                //reset text labels to reflect reset scores
                yourScore.setText("Your score = " + userOverallScore);
                comScore.setText("Computer score = " + computerOverallScore);
                extraScore.setText("Play a turn!");

                rollButton.setEnabled(true);
                holdButton.setEnabled(true);
            }
        });

        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update user's overall score by adding turn score & update label to reflect that
                userOverallScore += userTurnScore;

                if (userOverallScore >= 100) {
                    extraScore.setText("Computer wins! Press restart to play again.");
                    rollButton.setEnabled(false);
                    holdButton.setSaveEnabled(false);
                }
                else {
                    yourScore.setText("Your Score = " + userOverallScore);
                    //reset turn score and reset text label
                    userTurnScore = 0;
                    extraScore.setText("Turn Score = " + userTurnScore);
                    //now it's computer's turn
                    computerTurn();
                }

            }
        });

    }

    /**
     * helper method when it's the computer's turn
     * disables roll and hold buttons & while loop to loop over computer's turns
     */
    private void computerTurn() {



        final Handler handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {

                rollButton.setEnabled(false);
                holdButton.setEnabled(false);
                doARoll();
                computerTurnScore += diceValue;

                if (computerOverallScore < 100) {

                    if (computerTurnScore >= 20) {
                        //hold there
                        extraScore.setText("Turn Score = " + computerTurnScore);
                        computerOverallScore += computerTurnScore;
                        comScore.setText("Computer score = " + computerOverallScore);
                        //reset turn score and reset text label
                        computerTurnScore = 0;

                    }
                    else if (diceValue == 1) {
                        computerTurnScore = 0;
                        extraScore.setText("Turn Score = " + computerTurnScore);
                        computerOverallScore += computerTurnScore;
                        comScore.setText("Computer score = " + computerOverallScore);
                        // computerTurn = false;
                    }

                    else if (diceValue>1){
                        //pause to see image change
                        extraScore.setText("Turn Score = " + computerTurnScore);
                        handler.postDelayed(this, 500);
                    }


                }
                if (computerOverallScore>= 100) {
                    extraScore.setText("Computer wins! Press restart to play again.");
                    rollButton.setEnabled(false);
                    holdButton.setEnabled(false);

                }

            }
        };
        handler.postDelayed(r, 500);

        //re-enable buttons
        rollButton.setEnabled(true);
        holdButton.setEnabled(true);


    }

    /**
     * Method invoked when the roll button is pressed.
     * Contains rules of the game, when dice rolls a 1, turn gets 0, else turn gets # rolled.
     */
    private void doARoll() {
        //dice rolls a random #
        diceValue = random.nextInt(6-1) + 1;
        // Log.i("test7", "diceValue: "+diceValue);

        switch(diceValue) {
            case 1:
                dice.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice1, null));
                diceValue = 1;  //update score
                // extraScore.setText("Your Turn Score = " + 0);   //update turn score label
                break;

            case 2:
                dice.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice2, null));
                //userTurnScore += diceValue;
                // extraScore.setText("Your Turn Score = " + userTurnScore);
                break;

            case 3:
                dice.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice3, null));
                //userTurnScore += diceValue;
                // extraScore.setText("Your Turn Score = " + userTurnScore);
                break;

            case 4:
                dice.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice4, null));
                // userTurnScore += diceValue;
                //extraScore.setText("Your Turn Score = " + userTurnScore);
                break;

            case 5:
                dice.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice5, null));
                // userTurnScore += diceValue;
                //extraScore.setText("Your Turn Score = " + userTurnScore);
                break;
            case 6:
                dice.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.dice6, null));
                // userTurnScore += diceValue;
                // extraScore.setText("Your Turn Score = " + userTurnScore);
                break;
        }

    }


}