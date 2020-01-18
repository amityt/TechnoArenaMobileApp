package com.example.technoarenaapp;

import com.google.firebase.firestore.Exclude;

public class Note {

    private String devname,devname1;
    private String softwareversion;
    private String processor;
    private String imgurl;
    private String CPU,GPU;
    private String battery;
    private String display , frontcamera, maincamera, operatingsystem, sensors, storage_ram,videorecording;

    //Firestore always needs a Public Empty Constructor
    public Note(){
        //Public no-args constructor
    }

    public Note( String devname,String devname1,String softwareversion, String processor, String imgurl,String CPU, String GPU, String battery, String display, String frontcamera, String maincamera, String operatingsystem, String sensors,String storage_ram, String videorecording){
        this.devname = devname;
        this.devname1 = devname1;
        this.softwareversion = softwareversion;
        this.processor = processor;
        this.imgurl = imgurl;
        this.GPU = GPU;
        this.CPU = CPU;
        this.battery = battery;
        this.display=display;
        this.frontcamera=frontcamera;
        this.maincamera=maincamera;
        this.operatingsystem=operatingsystem;
        this.sensors=sensors;
        this.storage_ram=storage_ram;
        this.videorecording=videorecording;
    }

    public String getCPU() { return CPU; }
    public String getDevname1() { return devname1; }
    public String getDevname(){
        return devname;
    }
    public  String getSoftwareversion(){
        return softwareversion;
    }
    public  String getProcessor(){
        return processor;
    }
    public String getImgurl() { return imgurl; }
    public String getGPU() { return GPU; }
    public String getBattery() { return battery; }
    public String getDisplay() { return display; }
    public String getFrontcamera() { return frontcamera; }
    public String getMaincamera() { return maincamera; }
    public String getOperatingsystem() { return operatingsystem; }
    public String getSensors() { return sensors; }
    public String getStorage_ram() { return storage_ram; }
    public String getVideorecording() { return videorecording; }
}
