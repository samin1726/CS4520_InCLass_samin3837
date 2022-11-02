package com.example.cs4520_inclass_samin3837.inClass07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.cs4520_inclass_samin3837.R;

import java.util.Arrays;
import java.util.List;

public class NotesDisplayActivity extends AppCompatActivity implements AddFragment.IaddButtonActions {

    Notes notes;
    RecyclerView recyclerView;
    NotesAdapter notesAdapter;
    String accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_display);
        setTitle("Notes");
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, AddFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("addFragment")
                    .commit();
        }

        if (getIntent() != null && getIntent().getExtras() != null) {
            notes = (Notes) getIntent().getSerializableExtra("Notes");
            accessToken = getIntent().getStringExtra("Access Token");
        }

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        notesAdapter = new NotesAdapter(notes, accessToken);
        recyclerView.setAdapter(notesAdapter);

    }

    @Override
    public void addButtonClicked(Note note) {
        if (notes.getNotes() != null) {
            notes.getNotes().add(note);
            notesAdapter.notifyDataSetChanged();
        } else {
            Note[] newNotes = new Note[100];
            notes.setNotes(Arrays.asList(newNotes));
            notesAdapter.notifyDataSetChanged();
        }
    }

    public String sendAccessToken() {
        return  accessToken;
    }
}