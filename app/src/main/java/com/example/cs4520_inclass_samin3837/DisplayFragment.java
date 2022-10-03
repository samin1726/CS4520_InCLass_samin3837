package com.example.cs4520_inclass_samin3837;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayFragment extends Fragment {

    private ImageView profilePic;
    private ImageView moodPic;
    private TextView nameResult;
    private TextView emailResult;
    private TextView phoneTypeResult;
    private TextView moodResult;

    public DisplayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profilePic = view.findViewById(R.id.image_display_activity2);
        nameResult = view.findViewById(R.id.nameResult2);
        emailResult = view.findViewById(R.id.emailResult2);
        phoneTypeResult = view.findViewById(R.id.phonetype_display_activity2);
        moodResult = view.findViewById(R.id.mood_display_activity2);
        moodPic = view.findViewById(R.id.mood_image2);

        User user = (User) requireArguments().getSerializable("BundleKey2");
        profilePic.setImageResource(user.getAvatarImageID());
        nameResult.setText(user.getName());
        emailResult.setText(user.getEmail());
        phoneTypeResult.setText("I use " + user.getPhoneType() + "!");
        moodResult.setText("I am " + user.getMood() + "!");
        moodPic.setImageResource(user.getMoodImageID());
    }
}