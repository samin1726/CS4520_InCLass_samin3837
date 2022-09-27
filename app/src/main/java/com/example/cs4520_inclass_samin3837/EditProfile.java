package com.example.cs4520_inclass_samin3837;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private ImageView defaultAvatar;
    private RadioGroup options;
    private SeekBar moodBar;
    private TextView moodIndicatorText;
    private ImageView sadImage;
    private Button submitButton;
    private RadioButton selectedOption;
    private String mood;
    private int selectedAvatar;
    private int selectedEmotion;
    private ActivityResultLauncher<Intent> startActivityForResult
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                selectedAvatar = result.getData().getIntExtra("selectedImage", 0);
                defaultAvatar.setImageResource(selectedAvatar);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class02);
        setTitle("Edit Profile Activity");
        name = findViewById(R.id.edit_text_person_name);
        email = findViewById(R.id.edit_text_person_email);
        defaultAvatar = findViewById(R.id.default_avatar);
        options = findViewById(R.id.radio_group);
        moodBar = findViewById(R.id.seekBar);
        moodIndicatorText = findViewById(R.id.mood_indicator);
        sadImage = findViewById(R.id.sad_Image);
        submitButton = findViewById(R.id.submit_button);
        mood = "Happy";
        selectedEmotion = R.drawable.happy;
        selectedAvatar = R.drawable.select_avatar;
        selectedOption = findViewById(R.id.android_button);



        defaultAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this, SelectAvatar.class);
                startActivityForResult.launch(intent);
            }
        });

        options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedButton = findViewById(i);
                selectedOption = checkedButton;
            }
        });

        moodBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i == 0) {
                    selectedEmotion = R.drawable.angry;
                    sadImage.setImageResource(R.drawable.angry);
                    moodIndicatorText.setText("Your current mood: Angry");
                    mood = "Angry";
                }
                else if(i == 1) {
                    selectedEmotion = R.drawable.sad;
                    sadImage.setImageResource(R.drawable.sad);
                    moodIndicatorText.setText("Your current mood: Sad");
                    mood = "Sad";

                }
                else if(i == 2) {
                    selectedEmotion = R.drawable.happy;
                    sadImage.setImageResource(R.drawable.happy);
                    moodIndicatorText.setText("Your current mood: Happy");
                    mood = "Happy";
                }
                else {
                    selectedEmotion = R.drawable.awesome;
                    sadImage.setImageResource(R.drawable.awesome);
                    moodIndicatorText.setText("Your current mood: Awesome");
                    mood = "Awesome";
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().matches("") || (name.getText().toString().matches(""))) {
                    Toast.makeText(EditProfile.this, "enter an email and name!", Toast.LENGTH_LONG).show();
                }
                else {
                    String sendName = name.getText().toString();
                    String sendEmail = email.getText().toString();
                    String phoneType = selectedOption.getText().toString();
                    int emotionImageID = selectedEmotion;
                    int avatarID = selectedAvatar;
                    User user = new User(sendName, sendEmail, phoneType, mood, emotionImageID, avatarID);
                    Intent intentToDisplayActivity = new Intent(EditProfile.this, DisplayActivity.class);
                    intentToDisplayActivity.putExtra("user", user);
                    startActivity(intentToDisplayActivity);
                }
            }
        });





    }
}