package com.example.shadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shadproject.ui.reachout.ReachoutFragment;

public class dailyCheckIn extends AppCompatActivity {

    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_check_in);

        getSupportActionBar().hide();


        submit = findViewById(R.id.checkInSubmitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dailyCheckIn.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}