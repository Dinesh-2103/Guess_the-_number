package com.example.random_number_game;

import static android.os.Build.VERSION_CODES.R;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText Input;
    private TextView attempt;
    private int attemptCount = 0;
    private int generatedNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Input = findViewById(R.id.userInputEditText);
        attempt = findViewById(R.id.attemptTextView);

        Button enterButton = findViewById(R.id.enterButton);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMatch();
                Input.getText().clear();
            }
        });

        generateNewNumber();

        updateCount();
    }


    private void checkMatch() {
        String won="CONGRATULATION YOU WON THE MATCH!!!!";
        String lose="BETTER LUCK NEXT TIME ><";
        int userInput = Integer.parseInt(Input.getText().toString());
        if (userInput>100){
            Toast.makeText(getApplicationContext(), "INVALID INPUT", Toast.LENGTH_SHORT).show();
            Input.getText().clear();
        }  else if (userInput<generatedNumber) {
            Toast.makeText(getApplicationContext(), "WHY WON'T YOU GO WITH LARGE NUMBERS", Toast.LENGTH_SHORT).show();
        } else if (userInput>generatedNumber) {
            Toast.makeText(getApplicationContext(), "WHY WON'T YOU GO WITH SMALL NUMBERS", Toast.LENGTH_SHORT).show();
        }

        if (attemptCount >=19) {
            Toast.makeText(getApplicationContext(), "ATTEMPT OVER RESTART THE GAME", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, End_page.class);
            intent.putExtra("KEY_SEND",lose);
            startActivity(intent);
        } else if (userInput == generatedNumber) {
            Toast.makeText(getApplicationContext(), "CONGRATULATIONS YOU FOUND THE NUMBER", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, End_page.class);
            intent.putExtra("KEY_SEND",won);
            startActivity(intent);
            resetCount();
            generateNewNumber();
        }

        attemptCount++;

        updateCount();
    }

    private void generateNewNumber() {
        Random random = new Random();
        generatedNumber = random.nextInt(100);
    }

    private void resetCount() {
        attemptCount = 0;
        Input.getText().clear();
        updateCount();
    }

    private void updateCount() {
        attempt.setText(String.valueOf(attemptCount));
    }
}
