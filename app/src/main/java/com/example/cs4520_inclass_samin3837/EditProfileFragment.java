package com.example.cs4520_inclass_samin3837;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfileFragment extends Fragment {

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


    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("requestKey", this,
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        selectedAvatar = result.getInt("bundleKey");
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.edit_text_person_name2);
        email = view.findViewById(R.id.edit_text_person_email2);
        defaultAvatar = view.findViewById(R.id.default_avatar2);
        options = view.findViewById(R.id.radio_group_2);
        moodBar = view.findViewById(R.id.seekBar2);
        moodIndicatorText = view.findViewById(R.id.mood_indicator2);
        sadImage = view.findViewById(R.id.sad_Image2);
        submitButton = view.findViewById(R.id.submit_button2);
        mood = "Happy";
        selectedEmotion = R.drawable.happy;
        selectedAvatar = R.drawable.select_avatar;
        selectedOption = view.findViewById(R.id.android_button2);

        defaultAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainerView, AvatarFragment.class, null)
                    .addToBackStack("avatar")
                    .commit();
            }
        });

        options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedButton = view.findViewById(i);
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
                    Toast.makeText(getActivity(), "enter an email and name!", Toast.LENGTH_LONG).show();
                }
                else {
                    String sendName = name.getText().toString();
                    String sendEmail = email.getText().toString();
                    String phoneType = selectedOption.getText().toString();
                    int emotionImageID = selectedEmotion;
                    int avatarID = selectedAvatar;
                    User user = new User(sendName, sendEmail, phoneType, mood, emotionImageID, avatarID);
                    Bundle result = new Bundle();
                    result.putSerializable("BundleKey2", user);
                    getParentFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.fragmentContainerView, DisplayFragment.class, result)
                            .commit();
                }
            }
        });
    }
}