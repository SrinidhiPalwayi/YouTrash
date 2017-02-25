package com.example.myothiha09.m4cs2340.controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myothiha09.m4cs2340.R;
import com.example.myothiha09.m4cs2340.model.User;

//Team: 27

/**
 * The activity that allows the user to enter in their credentials.
 * They see this activity right after clicking on the LOGIN button.
 *
 * The user has the option of logging in or by cancelling.
 */

public class LoginActivity extends AppCompatActivity {

    //References to the text views.
    EditText username;
    EditText password;

    /**
     * Assign the edit text views.
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle
     *                           contains the data it most recently supplied in
     *                           onSaveInstanceState(Bundle).
     *                           Note: Otherwise it is null.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        username = (EditText) findViewById(R.id.userNameText);
        password = (EditText) findViewById(R.id.passwordText);
    }

    /**
     * When the login button is pressed, the login credentials
     * are first verified. If credentials are correct, the user
     * is redirected to their homescreen.
     * @param v The view that triggered this event.
     */
    public void onLoginPressed(View v) {
        if (checkPassword()) {
            Intent intent = new Intent(this, MainScreenActivity.class);
            intent.putExtra("Username", username.getText().toString());
            startActivity(intent);
        } else {
            AlertDialog dialog = createDialogBox();
            dialog.show();
        }
    }

    /**
     * Return to home screen if the cancel button is pressed.
     * @param v The view that triggered this event.
     */

    public void onCancelPressed(View v) {
        finish();
    }

    /**
     * Verifies the login credentials entered by the user.
     * @return Whether the login credentials are valid.
     */

    private boolean checkPassword() {
        String username2 = username.getText().toString();
        String password2 = password.getText().toString();
        for(User user: User.usersList) {
            if (user.getName().equals(username2)&&user.getPassword().equals(password2))
                return true;
        }
        return false;
    }

    /**
     * Displays an error if the login credentials are incorrect.
     * @return The error dialog.
     */
    private android.app.AlertDialog createDialogBox() {
        android.app.AlertDialog.Builder myDialogBuilder = new android.app.AlertDialog.Builder(this);
        myDialogBuilder.setTitle("Error!");
        myDialogBuilder.setMessage("Wrong password or username.");
        myDialogBuilder.setIcon(R.mipmap.ic_launcher);


        myDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        return myDialogBuilder.create();
    }

    /**
     * Overrides the onStop function to return to home screen.
     */

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
