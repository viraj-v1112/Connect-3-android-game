package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activeplayer = 0;
    boolean gameactive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if( gameState[tappedCounter] == 2 && gameactive){
            counter.setTranslationY(-1000f);

            gameState[tappedCounter] = activeplayer;

            if(activeplayer == 0){
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            }
            else{
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(3).setDuration(100);
        }

        for (int[] winningPosition : winningPositions) {

            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2) {

                gameactive = false;

                String winner ;
                if (gameState[winningPosition[0]] == 0) {
                    winner = "Yellow";
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");
                    LinearLayout linearLayout = findViewById(R.id.popuplinear);
                    linearLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    winner = "Red";
                    TextView winnerMessage2 = (TextView) findViewById(R.id.winnerMessage2);
                    winnerMessage2.setText(winner + " has won!");
                    LinearLayout linearLayout = findViewById(R.id.popuplinear2);
                    linearLayout.setVisibility(View.VISIBLE);
                }


            }
            else {
                boolean gameIsOver = true;

                for (int counterState : gameState) {
                    if (counterState == 2) gameIsOver = false;
                }

                if (gameIsOver) {
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText("It's a draw");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.popuplinear);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {

        gameactive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.popuplinear);
        layout.setVisibility(View.INVISIBLE);
        LinearLayout layout2 = (LinearLayout)findViewById(R.id.popuplinear2);
        layout2.setVisibility(View.INVISIBLE);

        activeplayer = 0;

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridback);

        for (int i = 0; i< gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }
}
