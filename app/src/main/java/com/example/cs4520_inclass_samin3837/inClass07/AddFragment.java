package com.example.cs4520_inclass_samin3837.inClass07;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cs4520_inclass_samin3837.R;
import com.example.cs4520_inclass_samin3837.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Reader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;
    private Note note;
    IaddButtonActions mListener;
    private String accessToken;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        EditText writeNote = view.findViewById(R.id.addNoteText);
        Button addButton = view.findViewById(R.id.addButton);
        Button logoutButton = view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotesDisplayActivity context = (NotesDisplayActivity) getContext();
                context.finish();
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note = new Note();
                note.setText(String.valueOf(writeNote.getText()));
                accessToken = mListener.sendAccessToken();
                RequestBody formBody = new FormBody.Builder()
                        .add("text", note.getText())
                        .build();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://dev.sakibnm.space:3000/api/note/post")
                        .addHeader("x-access-token", accessToken)
                        .post(formBody)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            ResponseBody responseBody = response.body();
                            try {
                                JSONObject rootJson = new JSONObject(responseBody.string());
                                JSONObject noteJson = rootJson.getJSONObject("note");
                                Note note2 = new Note();
                                note2.set_id(noteJson.getString("_id"));
                                note2.set_userId(noteJson.getString("userId"));
                                note2.set__v(noteJson.getString("__v"));
                                note2.setText(noteJson.getString("text"));
                                note.set__v(note2.get__v());
                                note.set_id(note2.get_id());
                                note.set_userId(note2.get_userId());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                mListener.addButtonClicked(note);
            }
        });
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IaddButtonActions) {
            mListener = (IaddButtonActions) context;
        }
        else {
            throw new RuntimeException(context.toString() + "must implement IaddButtonActions");
        }
    }

    public interface IaddButtonActions {
        void addButtonClicked(Note note);
        String sendAccessToken();
    }
}