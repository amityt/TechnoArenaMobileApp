package com.example.technoarenaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class GuestAccount extends AppCompatActivity implements View.OnClickListener {
    private ImageButton alert;
    private ImageButton msearch;
    private ImageButton login;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_account);
        alert = findViewById(R.id.display_alert);
        alert.setOnClickListener(this);
        msearch = findViewById(R.id.buttonsearch);
        msearch.setOnClickListener(this);
        login = findViewById(R.id.buttonlogin);
        login.setOnClickListener(this);

    }
    void displayalert(){
        new AlertDialog.Builder(this)
                .setTitle("Login Required!")
                .setMessage("Create an account to access this content!")
                .setPositiveButton("Create Account", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent login = new Intent(GuestAccount.this, newsignup.class);
                        startActivity(login);
                    }
                })
                .setNegativeButton("Back", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onClick(View v) {
        if(v==alert){
            displayalert();
        }
        if(v==msearch){
            Intent searchpage = new Intent(GuestAccount.this, guest_search.class);
            startActivity(searchpage);
        }
        if(v==login){
            Intent createacc = new Intent(GuestAccount.this, newsignup.class);
            startActivity(createacc);
        }
    }
}
