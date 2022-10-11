package com.example.cs4520_inclass_samin3837.inClass02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs4520_inclass_samin3837.R;
import com.example.cs4520_inclass_samin3837.User;

public class DisplayActivity extends AppCompatActivity {

    private ImageView profilePic;
    private ImageView moodPic;
    private TextView nameResult;
    private TextView emailResult;
    private TextView phoneTypeResult;
    private TextView moodResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        setTitle("Display Activity");

        profilePic = findViewById(R.id.image_display_activity);
        nameResult = findViewById(R.id.nameResult);
        emailResult = findViewById(R.id.emailResult);
        phoneTypeResult = findViewById(R.id.phonetype_display_activity);
        moodResult = findViewById(R.id.mood_display_activity);
        moodPic = findViewById(R.id.mood_image);

        if (getIntent() != null && getIntent().getExtras() != null) {
            User user = (User) getIntent().getSerializableExtra("user");
            profilePic.setImageResource(user.getAvatarImageID());
            nameResult.setText(user.getName());
            emailResult.setText(user.getEmail());
            phoneTypeResult.setText("I use " + user.getPhoneType() + "!");
            moodResult.setText("I am " + user.getMood() + "!");
            moodPic.setImageResource(user.getMoodImageID());
        }
    }
}