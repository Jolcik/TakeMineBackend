package com.pkostrzenski.takemine.controllers.request_models;

import javax.validation.constraints.NotBlank;

public class ChangePasswordRequest {

    @NotBlank
    String currentPassword;

    @NotBlank
    String newPassword;

    public ChangePasswordRequest(@NotBlank String currentPassword, @NotBlank String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
