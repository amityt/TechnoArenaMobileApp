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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class compare_devices extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "compare_devices";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookref = db.collection("Device Description");
    public TextView mdev,mdev2;
    public TextView mos,mos2;
    public TextView sversion,sversion2;
    public TextView mprocessor,mprocessor2;
    public TextView mcpu,mcpu2;
    public TextView mgpu,mgpu2;
    public TextView mdisplay,mdisplay2;
    public TextView mstorage,mstorage2;
    public TextView mmaincamera,mmaincamera2;
    public TextView mfrontcamera,mfrontcamera2;
    public TextView mvideo,mvideo2;
    public TextView msensors,msensors2;
    public TextView mbattery,mbattery2;
    public ImageButton compare_dev;
    public NestedScrollView displaydata;
    public EditText search1,search2;
    public ImageView goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_devices);
        compare_dev = findViewById(R.id.button8);
        search1 = findViewById(R.id.enterdevice1);
        search2 = findViewById(R.id.enterdevice2);
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
        mbattery = findViewById(R.id.batteryinfo);

        displaydata = findViewById(R.id.scrollviewauthtrue);

        mdev2 = findViewById(R.id.mdevice2);
        mos2 = findViewById(R.id.moperating2);
        sversion2 = findViewById(R.id.msoftwarever2);
        mprocessor2 = findViewById(R.id.mprocessor2);
        mcpu2 = findViewById(R.id.mcpu2);
        mgpu2 = findViewById(R.id.gpuinfo2);
        mdisplay2 = findViewById(R.id.displayinfo2);
        mstorage2 = findViewById(R.id.storageinfo2);
        mmaincamera2 = findViewById(R.id.maincaminfo2);
        mfrontcamera2 = findViewById(R.id.frontcaminfo2);
        mvideo2 = findViewById(R.id.videoinfo2);
        msensors2 = findViewById(R.id.sensorinfo2);
        mbattery2 = findViewById(R.id.batteryinfo2);
        goback = findViewById(R.id.backimage);
        goback.setOnClickListener(this);
        compare_dev.setOnClickListener(this);
    }

    protected void onStart() {
        super.onStart();
        displaydata.setVisibility(View.GONE);
    }

    void comparedevices(){
        String searchdevice1 = search1.getText().toString();
        String searchdevice2 = search2.getText().toString();
        searching1(searchdevice1);
        searching2(searchdevice2);
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
                            search1.setText("No device found, try again.");
                        }
                        else{
                            ImageView imgview1 = findViewById(R.id.imageView4);
                            if(data4.isEmpty()){
                                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/technoarenamobile.appspot.com/o/default_device.png?alt=media&token=422f7c1b-7761-4c5e-95f2-b05bbb5939ad").resize(250, 250)
                                        .centerCrop().into(imgview1);
                            }
                            else {
                                Picasso.get().load(data4).into(imgview1);
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
                Log.d(TAG, e.toString());
            }
        });
    }

    public void searching2(String  dname){
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
                            search2.setText("No device found, try again.");
                        }
                        else{
                            ImageView imgview2 = findViewById(R.id.imageView5);
                            if(data4.isEmpty()){
                                Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/technoarenamobile.appspot.com/o/default_device.png?alt=media&token=422f7c1b-7761-4c5e-95f2-b05bbb5939ad").resize(250, 250)
                                        .centerCrop().into(imgview2);
                            }
                            else {
                                Picasso.get().load(data4).into(imgview2);
                            }
                            mdev2.setText(data1);
                            mos2.setText(data2);
                            mprocessor2.setText(data3);
                            sversion2.setText(data5);
                            mcpu2.setText(data6);
                            mgpu2.setText(data7);
                            mdisplay2.setText(data8);
                            mstorage2.setText(data9);
                            mmaincamera2.setText(data10);
                            mfrontcamera2.setText(data11);
                            mvideo2.setText(data12);
                            msensors2.setText(data13);
                            mbattery2.setText(data14);
                        }}
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, e.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == compare_dev){
            comparedevices();
            displaydata.setVisibility(View.VISIBLE);
        }
        if(v == goback){
            Intent i = new Intent(compare_devices.this, fulldisplaypage.class);
            startActivity(i);
        }
    }
}
