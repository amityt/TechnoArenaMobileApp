package com.example.technoarenaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class userlogin extends AppCompatActivity implements View.OnClickListener {

    private EditText memail;
    private EditText mpassword;
    private Button login;
    private FirebaseAuth mAuth;
    private ImageView back;
    private TextView guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        memail = findViewById(R.id.editText);
        mpassword = findViewById(R.id.editText2);
        login = findViewById(R.id.button2);
        login.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        back = findViewById(R.id.imageView2);
        back.setOnClickListener(this);
        guest = findViewById(R.id.textView18);
        guest.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            updateUI(currentUser);
    }

    void userlogin(){
        String em = memail.getText().toString().trim();
        String pa = mpassword.getText().toString().trim();

        if(TextUtils.isEmpty(em)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(pa)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this, "Logging in, please wait!", Toast.LENGTH_LONG).show();
        mAuth.signInWithEmailAndPassword(em,pa)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            Toast.makeText(userlogin.this, "Welcome to TechnoArena!", Toast.LENGTH_LONG).show();
                            Intent i1 = new Intent(userlogin.this,fulldisplaypage.class);
                            startActivity(i1);
                        }
                        if(!task.isSuccessful()){
                            Toast.makeText(userlogin.this, "Email and Password doesn't exist, create an account", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(this, "Already signed-in!", Toast.LENGTH_SHORT).show();
            Intent i1 = new Intent(userlogin.this,fulldisplaypage.class);
            startActivity(i1);
            finish();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent home = new Intent(userlogin.this, MainActivity.class);
        startActivity(home);
    }

    @Override
    public void onClick(View v) {
        if(v==login){
            userlogin();
        }
        if(v==back){
            Intent home = new Intent(userlogin.this, MainActivity.class);
            startActivity(home);
        }
        if(v==guest){
            Intent home = new Intent(userlogin.this, GuestAccount.class);
            startActivity(home);
        }
    }
}
