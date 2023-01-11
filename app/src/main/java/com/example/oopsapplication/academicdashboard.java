package com.example.oopsapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class academicdashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academicdashboard);
    }
    public void gotostudy(android.view.View v)
    {

        Intent intent2 = new Intent(academicdashboard.this,studytodo.class);
        startActivity(intent2);
    }
    public void gotobooks(android.view.View v)
    {

        Intent intent3 = new Intent(academicdashboard.this,books.class);
        startActivity(intent3);
    }
    public void gotonotes(android.view.View v)
    {

        Intent intent4 = new Intent(academicdashboard.this,notes.class);
        startActivity(intent4);
    }
    public void gotoscratch(android.view.View v)
    {

        Intent intent5 = new Intent(academicdashboard.this,academicscratch.class);
        startActivity(intent5);
    }
}