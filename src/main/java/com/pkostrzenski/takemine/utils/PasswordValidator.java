package com.pkostrzenski.takemine.utils;

public class PasswordValidator {

    public PasswordValidator() { }

    public static boolean validatePassword(String password){
        if(password.length() < 8)
            return false;

        return true;
    }

}
