package com.example.technoarenaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class displayuserdetailsmain extends AppCompatActivity {
    private TextView mname;
    private TextView memail;
    private TextView muserid;
    private CircleImageView mimage;
    private FirebaseAuth mAuth;
    public String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayuserdetailsmain);
        mname = findViewById(R.id.textView14);
        memail = findViewById(R.id.textView15);
        muserid = findViewById(R.id.textView16);
        mimage = findViewById(R.id.profile_image);
        mAuth = FirebaseAuth.getInstance();
        getUserProfile();
    }


    public void getUserProfile() {
        final FirebaseUser muser = FirebaseAuth.getInstance().getCurrentUser();
        uid = muser.getUid();
        if (muser != null){
        DatabaseReference db=FirebaseDatabase.getInstance().getReference("Device Description");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String user_name = dataSnapshot.child(uid).child("username").getValue(String.class);
                mname.setText(user_name);
                String name = muser.getDisplayName();
                String email = muser.getEmail();
                String uid = muser.getUid();
                if(user_name == null){
                    mname.setText(name);
                }
                memail.setText(email);
                muserid.setText(uid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

            Uri photoUrl = muser.getPhotoUrl();
            if(photoUrl != null){
            Picasso.get().load(photoUrl).resize(300, 300)
                    .centerCrop().into(mimage);}
            else{
                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/technoarenamobile.appspot.com/o/default_avatar.png?alt=media&token=eaa5631c-9d2f-4192-98d4-d69d9e510b97").resize(300, 300)
                        .centerCrop().into(mimage);}
            }
        }

    public void goback(View view) {
        Intent i = new Intent(displayuserdetailsmain.this, fulldisplaypage.class);
        startActivity(i);
    }
}
