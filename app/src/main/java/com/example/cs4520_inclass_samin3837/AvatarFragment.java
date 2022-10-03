package com.example.cs4520_inclass_samin3837;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AvatarFragment extends Fragment {

    private int selectedImage;
    private ImageView female1;
    private ImageView female2;
    private ImageView female3;
    private ImageView male1;
    private ImageView male2;
    private ImageView male3;

    public AvatarFragment() {
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
        return inflater.inflate(R.layout.fragment_avatar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        female1 = view.findViewById(R.id.female_4);
        female2 = view.findViewById(R.id.female_5);
        female3 = view.findViewById(R.id.female_6);
        male1 = view.findViewById(R.id.male_4);
        male2 = view.findViewById(R.id.male_5);
        male3 = view.findViewById(R.id.male_6);

        female1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedImage = R.drawable.avatar_f_1;
                Bundle result = new Bundle();
                result.putInt("BundleKey", selectedImage);
                getParentFragmentManager().setFragmentResult("requestKey", result);
                getParentFragmentManager().popBackStack();
            }
        });

        female2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedImage = R.drawable.avatar_f_2;
                Bundle result = new Bundle();
                result.putInt("BundleKey", selectedImage);
                getParentFragmentManager().setFragmentResult("requestKey", result);
                getParentFragmentManager().popBackStack();
            }
        });

        female3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedImage = R.drawable.avatar_f_3;
                Bundle result = new Bundle();
                result.putInt("BundleKey", selectedImage);
                getParentFragmentManager().setFragmentResult("requestKey", result);
                getParentFragmentManager().popBackStack();
            }
        });

        male1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedImage = R.drawable.avatar_m_1;
                Bundle result = new Bundle();
                result.putInt("BundleKey", selectedImage);
                getParentFragmentManager().setFragmentResult("requestKey", result);
                getParentFragmentManager().popBackStack();
            }
        });

        male2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedImage = R.drawable.avatar_m_2;
                Bundle result = new Bundle();
                result.putInt("BundleKey", selectedImage);
                getParentFragmentManager().setFragmentResult("requestKey", result);
                getParentFragmentManager().popBackStack();
            }
        });

        male3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedImage = R.drawable.avatar_m_3;
                Bundle result = new Bundle();
                result.putInt("BundleKey", selectedImage);
                getParentFragmentManager().setFragmentResult("requestKey", result);
                getParentFragmentManager().popBackStack();
            }
        });
    }
}