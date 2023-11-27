package com.ruzger.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // View components
    private ImageView imageComputerchoice;
    private ImageView imageUserchoice;
    private Button buttonRock;
    private Button buttonPaper;
    private Button buttonScissors;
    private TextView textCounters;
    private AlertDialog.Builder alertDialog;

    // Random generation
    Random rnd = new Random();

    // Stats
    int userWin = 0;
    int computerWin = 0;
    int tie = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onInit();

        buttonScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextRound(2);
            }
        });

        buttonPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextRound(1);
            }
        });

        buttonRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextRound(0);
            }
        });
    }

    // 0 - rock, 1 - paper, 2 - scissors
    void nextRound(int userChoice) {
        int computerChoice = rnd.nextInt(3);

        changeImages(computerChoice, userChoice);

        if(userChoice == computerChoice){
            Toast.makeText(MainActivity.this, "Döntetlen kör!", Toast.LENGTH_SHORT).show();
            tie++;
        } else {
            if(userChoice == 0) {
                switch (computerChoice) {
                    case 1:
                        computerWin++;
                        Toast.makeText(MainActivity.this, "Ezt a kört a gép nyerte!", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        userWin++;
                        Toast.makeText(MainActivity.this, "Ezt a kört a felhasználó nyerte!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }

            if(userChoice == 1) {
                switch (computerChoice) {
                    case 2:
                        computerWin++;
                        Toast.makeText(MainActivity.this, "Ezt a kört a gép nyerte!", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        userWin++;
                        Toast.makeText(MainActivity.this, "Ezt a kört a felhasználó nyerte!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }

            if(userChoice == 2) {
                switch (computerChoice) {
                    case 0:
                        computerWin++;
                        Toast.makeText(MainActivity.this, "Ezt a kört a gép nyerte!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        userWin++;
                        Toast.makeText(MainActivity.this, "Ezt a kört a felhasználó nyerte!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        }

        redrawScoreboard();
        checkIfGameEnded();
    }

    void changeImages(int computerChoice, int userChoice) {
        switch (computerChoice){
            case 0:
                imageComputerchoice.setImageResource(R.drawable.rock);
                break;
            case 1:
                imageComputerchoice.setImageResource(R.drawable.paper);
                break;
            case 2:
                imageComputerchoice.setImageResource(R.drawable.scissors);
                break;
            default:
                imageComputerchoice.setImageResource(R.drawable.rock);
                break;
        }

        switch (userChoice){
            case 0:
                imageUserchoice.setImageResource(R.drawable.rock);
                break;
            case 1:
                imageUserchoice.setImageResource(R.drawable.paper);
                break;
            case 2:
                imageUserchoice.setImageResource(R.drawable.scissors);
                break;
            default:
                imageUserchoice.setImageResource(R.drawable.rock);
                break;
        }
    }

    void redrawScoreboard() {
        textCounters.setText("Eredmény: Ember: " + String.valueOf(userWin) + ", Computer: " + String.valueOf(computerWin) + ", Döntetlen: " + String.valueOf(tie));
    }

    void checkIfGameEnded() {
        if(computerWin == 3 || userWin == 3){
            if(computerWin == 3) {
                alertDialog.setTitle("Vereség");
            } else {
                alertDialog.setTitle("Győzelem");
            }
            alertDialog.setMessage("Szeretne új játékot játszani?");
            alertDialog.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    resetGame();
                }
            });
            alertDialog.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertDialog.show();
        }
    }

    void resetGame() {
        computerWin = 0;
        userWin = 0;
        tie = 0;
        redrawScoreboard();
        imageComputerchoice.setImageResource(R.drawable.rock);
        imageUserchoice.setImageResource(R.drawable.rock);
    }
    void onInit() {
        imageComputerchoice = findViewById(R.id.imageComputerChoice);
        imageUserchoice = findViewById(R.id.imageUserchoice);
        buttonRock = findViewById(R.id.buttonRock);
        buttonPaper = findViewById(R.id.buttonPaper);
        buttonScissors = findViewById(R.id.buttonScissors);
        textCounters = findViewById(R.id.textCounters);
        alertDialog = new AlertDialog.Builder(MainActivity.this);

    }
}