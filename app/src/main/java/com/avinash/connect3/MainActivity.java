package com.avinash.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //0 means o and 1 means x
    int activePlayer = 0;

    boolean gameActive = true;

    //2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view){

        ImageView counter=(ImageView)view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter]==2 && gameActive) {

            gameState[tappedCounter]=activePlayer;

            if (activePlayer == 0) {

                counter.setTranslationY(-1000f);

                counter.setImageResource(R.drawable.o);

                counter.animate().translationYBy(1000f).rotation(720).setDuration(400);

                activePlayer = 1;
            } else {

                counter.setTranslationY(1000f);

                counter.setImageResource(R.drawable.x);

                counter.animate().translationYBy(-1000f).rotation(720).setDuration(400);

                activePlayer = 0;
            }

            for(int[] winningPosition : winningPositions){

                if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                                gameState[winningPosition[0]]!=2){

                    gameActive = false;

                    String winner = "x";

                    if(gameState[winningPosition[0]] == 0){

                        winner = "o";

                    }

                    TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + " has won!!");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);
                } else {

                    boolean gameOver = true;

                    for(int counterState : gameState){

                        if (counterState == 2){

                            gameOver = false;

                        }

                    }

                    if(gameOver){

                        TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);

                        winnerMessage.setText("Its draw!!");

                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);

                    }

                }

            }

        }

    }

    public void playAgain(View view){

        gameActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        //gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

        for(int i =0; i < gameState.length; i++){

            gameState[i]=2;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
