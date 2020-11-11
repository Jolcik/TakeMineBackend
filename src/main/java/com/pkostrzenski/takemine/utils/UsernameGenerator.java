package com.pkostrzenski.takemine.utils;

public class UsernameGenerator {

    public static String generateUsername(){
        return generateUsername(7);
    }

    public static String generateUsername(int howManyChars){
        String POSSIBLE_CHARACTERS = "0123456789";
        StringBuilder sb = new StringBuilder(howManyChars);

        for (int i = 0; i < howManyChars; i++) {
            int index = (int)(POSSIBLE_CHARACTERS.length() * Math.random());
            sb.append(POSSIBLE_CHARACTERS.charAt(index));
        }

        return sb.toString();
    }
}
