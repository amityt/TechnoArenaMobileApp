package com.example.technoarenaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class fulldisplaypage extends AppCompatActivity implements View.OnClickListener {

    private ImageButton logout;
    private ImageButton search;
    private FirebaseAuth mAuth;
    private ImageView userdetail;
    private ImageView mauthcompare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fulldisplaypage);
        userdetail = findViewById(R.id.authaccountdetails);
        logout = findViewById(R.id.authbuttonlogout);
        search = findViewById(R.id.authbuttonsearch);
        mauthcompare = findViewById(R.id.authcompare);
        mauthcompare.setOnClickListener(this);
        search.setOnClickListener(this);
        logout.setOnClickListener(this);
        userdetail.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, userlogin.class));
        }
    }

    @Override
    public void onClick(View v) {
        if(v == logout) {
            mAuth.signOut();
            finish();
            Toast.makeText(this, "Logging you out!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(fulldisplaypage.this, userlogin.class);
            startActivity(i);
        }
        if(v == userdetail){
            Intent i = new Intent(fulldisplaypage.this, displayuserdetailsmain.class);
            startActivity(i);
        }
        if(v == search){
            Intent i = new Intent(fulldisplaypage.this, authSearch.class);
            startActivity(i);
        }
        if(v == mauthcompare){
            Intent i = new Intent(fulldisplaypage.this, compare_devices.class);
            startActivity(i);
        }
    }
}
