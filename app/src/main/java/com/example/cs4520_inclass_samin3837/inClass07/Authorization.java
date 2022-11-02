package com.example.cs4520_inclass_samin3837.inClass07;

public class Authorization {
    String auth;
    String token;

    public Authorization() {
    }

    public Authorization(String auth, String token) {
        this.auth = auth;
        this.token = token;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
