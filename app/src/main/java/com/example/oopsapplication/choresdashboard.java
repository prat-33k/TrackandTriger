package com.example.oopsapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class choresdashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choresdashboard);
    }
    public void gotochorestodo(android.view.View v)
    {

        Intent intent2 = new Intent(choresdashboard.this,chorestodo.class);
        startActivity(intent2);
    }
    public void gotogrocery(android.view.View v)
    {

        Intent intent3 = new Intent(choresdashboard.this,grocery.class);
        startActivity(intent3);
    }
    public void gotomedical(android.view.View v)
    {

        Intent intent4 = new Intent(choresdashboard.this,medical.class);
        startActivity(intent4);
    }
    public void gotoscratch(android.view.View v)
    {

        Intent intent5 = new Intent(choresdashboard.this,choresscratch.class);
        startActivity(intent5);
    }
}