package com.example.cs4520_inclass_samin3837.inClass07;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Notes implements Serializable {
    private List<Note> notes;

    @Override
    public String toString() {
        return "Notes{" +
                "notes=" + notes +
                '}';
    }

    public Notes(List<Note> notes) {
        this.notes = notes;
    }

    public Notes() {
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
