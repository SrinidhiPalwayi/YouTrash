package com.example.myothiha09.m4cs2340.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myothiha09.m4cs2340.R;

// Team 27

/**
 * This is the first activity that the user sees when opening the app.
 * Gives the option of logging in or registering.
 */

public class WelcomeActivity extends AppCompatActivity {

    /**
     * Initializes the activity.
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle
     *                           contains the data it most recently supplied in
     *                           onSaveInstanceState(Bundle).
     *                           Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
    }

    /**
     * Handles when one of the buttons is clicked.
     * @param v The view that triggered the event
     */
    public void onClick(View v) {
        //If login button was selected, go the LoginActivity.
        if (v.getId() == R.id.loginButton) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

            //If the register button was selected, to the UserDetailsActivity.
        } else if (v.getId() == R.id.registrationButton) {
            Intent intent = new Intent(this, UserDetailsActivity.class);
            startActivity(intent);
        }
    }


}
