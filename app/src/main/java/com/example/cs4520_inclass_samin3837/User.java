package com.example.cs4520_inclass_samin3837;

import java.io.Serializable;

public class User implements Serializable {

    private String Name;
    private String Email;
    private String phoneType;
    private String mood;
    private int moodImageID;
    private int avatarImageID;


    public User(String name, String email, String phone, String moodString, int moodImage, int avatarImage) {
        Name = name;
        Email = email;
        phoneType = phone;
        mood = moodString;
        moodImageID = moodImage;
        avatarImageID = avatarImage;
    }

    public User() {

    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public String getMood() {
        return mood;
    }

    public int getMoodImageID() {
        return moodImageID;
    }

    public int getAvatarImageID() {
        return avatarImageID;
    }
}
