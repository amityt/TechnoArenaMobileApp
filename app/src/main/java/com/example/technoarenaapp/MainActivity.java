package com.example.technoarenaapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void userlogin(View view) {
        Intent  newlogin = new Intent(MainActivity.this, userlogin.class);
        startActivity(newlogin);
    }

    public void newsignup(View view) {
        Intent  newsignup = new Intent(MainActivity.this, newsignup.class);
        startActivity(newsignup);
    }
}
