package com.example.random_number_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class End_page extends AppCompatActivity {
    Button restart;
    TextView showmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_page);
        restart=findViewById(R.id.restart);
        showmessage=findViewById(R.id.shwmessge);
        Intent receive=getIntent();
        String shwvalue=receive.getStringExtra("KEY_SEND");
        showmessage.setText(shwvalue);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(End_page.this,SplashScreen.class);
                startActivity(intent);
            }
        });
    }
}