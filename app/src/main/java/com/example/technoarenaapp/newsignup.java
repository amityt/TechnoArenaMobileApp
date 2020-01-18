package com.example.technoarenaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class newsignup extends AppCompatActivity implements View.OnClickListener{

    private EditText memail;
    private EditText mpassword;
    private EditText mpassword2;
    private EditText musername;
    private Button msignup;
    private SignInButton googlesignup;
    private DatabaseReference mdatabasereference;
    private FirebaseDatabase mfirebaseDatabase;
    private ImageView mimgview;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "newsignup";
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsignup);
        memail = findViewById(R.id.getemail);
        mpassword = findViewById(R.id.password1);
        mpassword2 = findViewById(R.id.password2);
        musername = findViewById(R.id.getname);
        msignup = findViewById(R.id.signup);
        mimgview = findViewById(R.id.imageView2);
        googlesignup = findViewById(R.id.sign_in_button);
        mAuth = FirebaseAuth.getInstance();
        mdatabasereference = FirebaseDatabase.getInstance().getReference("Device Description");
        msignup.setOnClickListener(this);
        mimgview.setOnClickListener(this);
        googlesignup.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent home = new Intent(newsignup.this, MainActivity.class);
        startActivity(home);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
                updateUI(null);
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(newsignup.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void redg() {
        final String username = musername.getText().toString();
        final String email = memail.getText().toString();
        String password = mpassword.getText().toString();
        String password2 = mpassword2.getText().toString();
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password2)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.equals(password2)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                user info = new user(
                                            username,
                                            email
                                );

                                FirebaseDatabase.getInstance().getReference("Device Description")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(newsignup.this, "Successfully registered", Toast.LENGTH_LONG).show();
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        if(currentUser!=null) {
                                            signOut();
                                        }
                                        Intent i = new Intent(newsignup.this,userlogin.class);
                                        startActivity(i);
                                    }
                                });
                            } else {
                                Toast.makeText(newsignup.this, "Registration Error", Toast.LENGTH_LONG).show();
                            }

                        }
                    });


        }
        else
        {
            Toast.makeText(this, "Passwords are different...", Toast.LENGTH_SHORT).show();

        }

    }

    private void signOut() {
        mAuth.signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            String personEmail = user.getEmail();
            Toast.makeText(this, "Signed-up with "+ personEmail, Toast.LENGTH_SHORT).show();
            Intent display = new Intent(newsignup.this, fulldisplaypage.class);
            startActivity(display);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == msignup){
                redg();
        }
        if(v == mimgview){
            Intent i = new Intent(newsignup.this, MainActivity.class);
            startActivity(i);
        }
        if(v == googlesignup){
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if(currentUser!=null) {
                String personEmail = currentUser.getEmail();
                Toast.makeText(this, "Already signed-in with "+ personEmail, Toast.LENGTH_SHORT).show();
                Intent display = new Intent(newsignup.this, fulldisplaypage.class);
                startActivity(display);
            }
            else{
                signIn();
            }
        }
    }
}

