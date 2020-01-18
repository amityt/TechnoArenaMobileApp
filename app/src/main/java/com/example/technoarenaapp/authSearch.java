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
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class authSearch extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "authSearch";
    public EditText search1;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookref = db.collection("Device Description");
    public TextView mdev;
    public TextView mos;
    public TextView sversion;
    public TextView mprocessor;
    public TextView mcpu;
    public TextView mgpu;
    public TextView mdisplay;
    public TextView mstorage;
    public TextView mmaincamera;
    public TextView mfrontcamera;
    public TextView mvideo;
    public TextView msensors;
    public TextView mbattery;
    public Button display;
    public NestedScrollView displaydata;
    public ImageView mnoresult1;
    public TextView mnoresult2;
    public TextView mnoresult3;
    public ImageView goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_search);
        display = findViewById(R.id.button8);
        search1 = findViewById(R.id.enterdevice);
        mnoresult1 = findViewById(R.id.noresult1);
        mnoresult2 = findViewById(R.id.noresult2);
        mnoresult3 = findViewById(R.id.noresult3);
        mdev = findViewById(R.id.textView20);
        mos = findViewById(R.id.textView22);
        sversion = findViewById(R.id.textView24);
        mprocessor = findViewById(R.id.textView26);
        mcpu = findViewById(R.id.textView30);
        mgpu = findViewById(R.id.gpuinfo);
        mdisplay = findViewById(R.id.displayinfo);
        mstorage = findViewById(R.id.storageinfo);
        mmaincamera = findViewById(R.id.maincaminfo);
        mfrontcamera = findViewById(R.id.frontcaminfo);
        mvideo = findViewById(R.id.videoinfo);
        msensors = findViewById(R.id.sensorinfo);
        displaydata = findViewById(R.id.scrollviewauthtrue);
        mbattery = findViewById(R.id.batteryinfo);
        goback = findViewById(R.id.backimage);
        goback.setOnClickListener(this);
        display.setOnClickListener(this);
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
                    String data6 ="";
                    String data7 ="";
                    String data8 ="";
                    String data9 ="";
                    String data10 ="";
                    String data11 ="";
                    String data12 ="";
                    String data13 ="";
                    String data14 ="";
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots) {
                            Note note = documentSnapshot.toObject(Note.class);
                            String mdevicename = note.getDevname();
                            String mandver = note.getOperatingsystem();
                            String processor = note.getProcessor();
                            String software = note.getSoftwareversion();
                            String imgurl = note.getImgurl();
                            String mcpu1 = note.getCPU();
                            String mgpu1 = note.getGPU();
                            String mdisplay1 = note.getDisplay();
                            String mstorage1 = note.getStorage_ram();
                            String mcam1 = note.getMaincamera();
                            String fcam1 = note.getFrontcamera();
                            String mvideo1 = note.getVideorecording();
                            String msensor1 = note.getSensors();
                            String mbattery1 = note.getBattery();
                            data1 += mdevicename ;
                            data2 += mandver;
                            data3 += processor;
                            data4 += imgurl;
                            data5 += software;
                            data6 += mcpu1;
                            data7 += mgpu1;
                            data8 += mdisplay1;
                            data9 += mstorage1;
                            data10 += mcam1;
                            data11 += fcam1;
                            data12 += mvideo1;
                            data13 += msensor1;
                            data14 += mbattery1;
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
                                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/technoarenamobile.appspot.com/o/default_device.png?alt=media&token=422f7c1b-7761-4c5e-95f2-b05bbb5939ad").resize(250, 250)
                                        .centerCrop().into(imgview);
                            }
                            else {
                                Picasso.get().load(data4).into(imgview);
                            }
                            mdev.setText(data1);
                            mos.setText(data2);
                            mprocessor.setText(data3);
                            sversion.setText(data5);
                            mcpu.setText(data6);
                            mgpu.setText(data7);
                            mdisplay.setText(data8);
                            mstorage.setText(data9);
                            mmaincamera.setText(data10);
                            mfrontcamera.setText(data11);
                            mvideo.setText(data12);
                            msensors.setText(data13);
                            mbattery.setText(data14);
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

    @Override
    public void onClick(View v) {
        if(v==display){
            viewdevice();
        }
        if(v == goback){
            Intent i = new Intent(authSearch.this, fulldisplaypage.class);
            startActivity(i);
        }
    }
}
