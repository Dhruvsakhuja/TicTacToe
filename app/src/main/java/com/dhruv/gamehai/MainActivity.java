package com.dhruv.gamehai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    int count = 0;
    boolean gameIsActive = true;

    // 2 MEANS unplayed

    int[] gameState={2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){

        // 0=yellow 1=red




        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]== 2 && gameIsActive) {

            counter.setTranslationY(-1000f);

            gameState[tappedCounter]= activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }count++;
            counter.animate().translationYBy(1000f).rotationYBy(360f).setDuration(500);
        }
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        TextView textView = (TextView)findViewById(R.id.textView);
        for(int[] winningPosition : winningPositions){
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&& gameState[winningPosition[1]]==gameState[winningPosition[2]]&& gameState[winningPosition[0]]!=2){

                System.out.println(gameState[winningPosition[0]]);
                // Someone has won!!!


                if(gameState[winningPosition[0]]==0) {
                    textView.setText("Yellow has won the game");
                    gameIsActive= false;

               }
               else if(gameState[winningPosition[0]]==1)
                {
                    textView.setText("Red has won the game");
                    gameIsActive = false;
                }layout.setVisibility(View.VISIBLE);

            }
            else if(count == 9){
                textView.setText("game is draw");
                gameIsActive = false;
                layout.setVisibility(View.VISIBLE);
            }

        }
        }

        public void playAgain(View view){
        count=0;
            LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
            layout.setVisibility(View.INVISIBLE);
            activePlayer=0;
            gameIsActive = true;
            // 2 MEANS unplayed


            for(int i=0;i<9;i++){
                gameState[i]=2;
            }
            GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
            for(int i=0; i<gridLayout.getChildCount();i++)
            {
                ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
            }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
