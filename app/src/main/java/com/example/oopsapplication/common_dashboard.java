package com.example.oopsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class common_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_dashboard);
        TextView txtview = (TextView) findViewById(R.id.textView2);


    }
    public void gotogrocery(android.view.View v)
    {

        Intent intent2 = new Intent(common_dashboard.this,grocery.class);
        startActivity(intent2);
    }
    public void gotomedical(android.view.View v)
    {
        Intent intent2 = new Intent(common_dashboard.this,medical.class);
        startActivity(intent2);
    }
    public void gotoothers(android.view.View v)
    {
        Intent intent2 = new Intent(common_dashboard.this,others.class);
        startActivity(intent2);
    }
    public void gototodo(android.view.View v)
    {
        Intent intent2 = new Intent(common_dashboard.this,Dashboard.class);
        startActivity(intent2);
    }
}