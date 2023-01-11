package com.example.oopsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button professional;
    Button homemaker;
    Button bachelor;
    Button reminder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        professional=(Button) findViewById(R.id.button4);
        professional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,workdashboard.class);
                //intent1.putExtra("name",((Button)v).getText().toString());
                startActivity(intent1);
            }
        });
        homemaker =(Button) findViewById(R.id.button5);
        homemaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,choresdashboard.class);
                //intent2.putExtra("name",((Button)v).getText().toString());
                startActivity(intent2);
            }
        });
        bachelor=(Button) findViewById(R.id.button6);
        bachelor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this,academicdashboard.class);
                //intent3.putExtra("name",((Button)v).getText().toString());
                startActivity(intent3);
            }
        });
        reminder=(Button) findViewById(R.id.reminder);
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MainActivity.this,MainActivityReminder.class);
                startActivity(intent4);

            }
        });

    }
}
