package com.example.askme;

public class data {
    private static data instance;
    private String username;

    private data() {
        // Private constructor to prevent instantiation
    }

    public static data getInstance() {
        if (instance == null) {
            instance = new data();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
