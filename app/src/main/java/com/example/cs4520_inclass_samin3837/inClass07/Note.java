package com.example.cs4520_inclass_samin3837.inClass07;

import java.io.Serializable;

public class Note implements Serializable {
    private String _id;
    private String _userId;
    private String text;
    private String __v;

    public Note() {
    }

    public Note(String _id, String _userId, String text, String __v) {
        this._id = _id;
        this._userId = _userId;
        this.text = text;
        this.__v = __v;
    }

    @Override
    public String toString() {
        return "Note{" +
                "_id='" + _id + '\'' +
                ", _userId='" + _userId + '\'' +
                ", text='" + text + '\'' +
                ", __v='" + __v + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_userId() {
        return _userId;
    }

    public void set_userId(String _userId) {
        this._userId = _userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }
}
