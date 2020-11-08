package com.pkostrzenski.takemine.controllers.request_models;

import javax.validation.constraints.NotNull;

public class RegisterRequest {

    @NotNull
    String email;
    @NotNull
    String password;

    public RegisterRequest() { }

    public RegisterRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
