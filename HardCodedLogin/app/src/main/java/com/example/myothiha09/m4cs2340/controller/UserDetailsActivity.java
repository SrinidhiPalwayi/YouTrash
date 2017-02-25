package com.example.myothiha09.m4cs2340.controller;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;

import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.model.User;
import com.example.myothiha09.m4cs2340.model.UserType;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

// Team 27

/**
 * This activity allows a user to edit their account or create a new account.
 * This activity is called when the user presses "Registration" or "Edit Profile"
 */
public class UserDetailsActivity extends AppCompatActivity {

    /**
     * References to the views.
     */
    EditText userName;
    EditText password;
    EditText email;
    EditText Quad;
    Spinner userType;
    boolean newAccount;
    GoogleApiClient mGoogleApiClient;
    private String x;

    /**
     * Sets references to all the views, creates the adapter,
     * sets the views to the user details the user is just editing their profile,
     * and adds event handlers to the buttons.
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle
     *                           contains the data it most recently supplied in
     *                           onSaveInstanceState(Bundle).
     *                           Note: Otherwise it is null.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userName = (EditText) findViewById(R.id.name_field);
        password = (EditText) findViewById(R.id.password_field);
        email = (EditText) findViewById(R.id.email_field);
        Button registerButton = (Button) findViewById(R.id.registerButton);
        Button locationButt = (Button) findViewById(R.id.locationButt);
        Quad = (EditText) findViewById(R.id.Quad);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, User.userTypeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Sets the views to the current details of the user if the user is just
        //editing their profile.
        if (getIntent().hasExtra(User.ARG_USER)) {
            newAccount = false;
            User user = getIntent().getParcelableExtra(User.ARG_USER);
            userName.setText(user.getName());
            userName.setEnabled(false);
            password.setText(user.getPassword());
            email.setText(user.getEmail());
            registerButton.setText("Save Changes");
            locationButt.setText("Set Home");
        } else {
            newAccount = true;
        }

        System.out.println("got here!!!");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
        String Quadrant=setQuadrant();

        //Creates the user if the register button is pressed.
        //But first validates all the input the user has entered.

        locationButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Quad.setText("NE");

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!getIntent().hasExtra(User.ARG_USER) && checkUsername()) {
                    Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_LONG).show();
                } else if (userName.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please input a username", Toast.LENGTH_LONG).show();
                } else if (password.getText().toString().length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password requirement not met", Toast.LENGTH_LONG).show();
                }  else if (email.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please input an email", Toast.LENGTH_LONG).show();
                }  else {
                    if (getIntent().hasExtra(User.ARG_USER)) {
                        for (User user : User.usersList) {
                            if (user.getName().equals(userName.getText().toString())) {
                                user.setPassword(password.getText().toString());
                                user.setEmail(email.getText().toString());
                                user.setUserType(UserType.USER);
                            }
                        }
                        finish();
                    }
                    else {
                        User user = new User(userName.getText().toString(),
                                password.getText().toString(),
                                email.getText().toString(),
                                UserType.USER);
                        User.usersList.add(user);
                        Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                        intent.putExtra("Username", user.getName());
                        startActivity(intent);
                    }
                }
            }
        });


        //Event handler for when the user clicks the cancel button.
        Button registerCancel = (Button) findViewById(R.id.registerCancel);
        registerCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }




    /**
     * Checks to see if the username is not taken.
     * @return if the username was taken or not (true if taken).
     */
    private boolean checkUsername() {
        String username2 = userName.getText().toString();
        for(User user: User.usersList) {
            if (user.getName().equals(username2))
                return true;
        }
        return false;
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

    /**
     * Handles if this activity is stopped.
     */
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
