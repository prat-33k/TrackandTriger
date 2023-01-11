package com.example.oopsapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class workdashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workdashboard);
    }
    public void gotoworktodo(android.view.View v)
    {

        Intent intent2 = new Intent(workdashboard.this,worktodo.class);
        startActivity(intent2);
    }
    public void gotoschedule(android.view.View v)
    {

        Intent intent3 = new Intent(workdashboard.this,schedule.class);
        startActivity(intent3);
    }
    public void gotodecision(android.view.View v)
    {

        Intent intent4 = new Intent(workdashboard.this,decision.class);
        startActivity(intent4);
    }
    public void gotoscratch(android.view.View v)
    {

        Intent intent5 = new Intent(workdashboard.this,workscratch.class);
        startActivity(intent5);
    }

}