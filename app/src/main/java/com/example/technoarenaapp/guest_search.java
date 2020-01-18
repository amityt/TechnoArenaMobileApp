package com.example.technoarenaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class guest_search extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "DisplayData";
    public EditText search1;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookref = db.collection("Device Description");
    public TextView mdev;
    public TextView mos;
    public TextView sversion;
    public TextView mprocessor;
    public Button display;
    public NestedScrollView displaydata;
    public ImageView mnoresult1;
    public TextView mnoresult2;
    public TextView mnoresult3;
    public TextView display1imgurl;
    public ImageView go_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_search);
        search1 = findViewById(R.id.enterdevice);
        mnoresult1 = findViewById(R.id.noresult1);
        mnoresult2 = findViewById(R.id.noresult2);
        mnoresult3 = findViewById(R.id.noresult3);
        display1imgurl = findViewById(R.id.textView31);
        mdev = findViewById(R.id.textView20);
        mos = findViewById(R.id.textView22);
        sversion = findViewById(R.id.textView24);
        mprocessor = findViewById(R.id.textView26);
        displaydata = findViewById(R.id.scrollviewtrue);
        go_back = findViewById(R.id.imageView7);
        go_back.setOnClickListener(this);
        display = findViewById(R.id.button8);
        display.setOnClickListener(this);
    }
    protected void onStart() {
        super.onStart();
        displaydata.setVisibility(View.GONE);
        displaytrue();
    }

    public void viewdevice() {
        String searchdevice1 = search1.getText().toString();
        searching1(searchdevice1);
    }

    public void searching1(String  dname){
        notebookref.whereEqualTo("devname1",dname.toLowerCase())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

                    String data1 ="";
                    String data2 ="";
                    String data3 ="";
                    String data4 ="";
                    String data5 ="";
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots) {
                            Note note = documentSnapshot.toObject(Note.class);
                            String mdevicename = note.getDevname();
                            String mandver = note.getOperatingsystem();
                            String processor = note.getProcessor();
                            String software = note.getSoftwareversion();
                            String imgurl = note.getImgurl();
                            data1 += mdevicename ;
                            data2 += mandver;
                            data3 += processor;
                            data4 += imgurl;
                            data5 += software;
                        }
                        if(queryDocumentSnapshots.isEmpty()){
                            displaydata.setVisibility(View.GONE);
                            nodisplay();
                        }
                        else{
                            displaydata.setVisibility(View.VISIBLE);
                            displaytrue();
                            ImageView imgview = findViewById(R.id.imageView4);
                            if(data4.isEmpty()){
                                imgview.setVisibility(View.GONE);
                                display1imgurl.setText("Preview unavailable.");
                            }
                            else {
                                Picasso.get().load(data4).into(imgview);
                            }
                            mdev.setText(data1);
                            mos.setText(data2);
                            mprocessor.setText(data3);
                            sversion.setText(data5);
                        }}
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mnoresult1.setVisibility(View.VISIBLE);
                mnoresult2.setVisibility(View.VISIBLE);
                mnoresult3.setVisibility(View.VISIBLE);
                Log.d(TAG, e.toString());
            }
        });
    }
     public void nodisplay(){
         mnoresult1.setVisibility(View.VISIBLE);
         mnoresult2.setVisibility(View.VISIBLE);
         mnoresult3.setVisibility(View.VISIBLE);
     }

     public void displaytrue(){
         mnoresult1.setVisibility(View.GONE);
         mnoresult2.setVisibility(View.GONE);
         mnoresult3.setVisibility(View.GONE);
     }

    @Override
    public void onClick(View v) {
        if(v==display){
            viewdevice();
        }
        if(v == go_back){
            Intent i = new Intent(guest_search.this, fulldisplaypage.class);
            startActivity(i);
        }
    }
}
