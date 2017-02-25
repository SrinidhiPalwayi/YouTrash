package com.example.myothiha09.m4cs2340.controller;

import android.Manifest;
import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Randy on 2/25/2017.
 */

public class MyLocationConnection extends Activity {
    private String x;
    GoogleApiClient mGoogleApiClient;

/*
 public MyLocationConnection() {
        super("YouTrash Background");
        onCreate();


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
        String Quadrant=setQuadrant();




    }

 */


    public void onHandleIntent(Intent intent) {
        // do stuff
    }
    public String setQuadrant()
    {
        Location mLastLocation=null;
        x="";
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation!=null)
        {
            x=findQuadrant(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        }
        else
            x="NE";
        return x;
    }

    public String getQuadrant() {
        return x;
    }

    public String findQuadrant(double Lat, double Long)
    {
        if (Lat>33.7541)
            if (Long>84.3915)
                return "NE";
            else
                return "NW";
        if (Long>84.3915)
            return "SE";
        return "SW";
    }
    public void onCreate()
    {

        System.out.println("got here!!!");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
        String Quadrant=setQuadrant();

    }
}

