package com.pkostrzenski.takemine.controllers.request_models;

public class AuthenticationResponse {

    private int id;
    private final String username;
    private final String token;

    public AuthenticationResponse(int id, String username, String token) {
        this.id = id;
        this.username = username;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}
