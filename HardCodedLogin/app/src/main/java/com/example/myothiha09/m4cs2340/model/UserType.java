package com.example.myothiha09.m4cs2340.model;

import java.io.Serializable;

// Team 27

/**
 * An enum that represents the user authorization level.
 * Each entry consists of the actual label, and the position in the spinner.
 */

public enum UserType implements Serializable {
    /**
     * First parameter is the string label for each user type,
     * second parameter is the position the user type has in the spinner.
     */
    EMPTY("", 0),
    USER("User", 1),
    WORKER("Worker", 2),
    MANAGER("Manager", 3),
    ADMIN("Admin", 4);

    String user;
    int position;
    private UserType(String str, int position) {
        user = str;
        this.position = position;
    }

    // Getters and setters for the attributes of each enum.
    @Override
    public String toString() {
        return user;
    }

    public String getUser() {
        return user;
    }

    public int getPosition() {
        return position;
    }
}
