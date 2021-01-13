package com.pkostrzenski.takemine.controllers.request_models;

import javax.validation.constraints.NotEmpty;


public class AddFirebaseTokenRequest {

    @NotEmpty
    private String token;

    public AddFirebaseTokenRequest() { }

    public AddFirebaseTokenRequest(@NotEmpty String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
