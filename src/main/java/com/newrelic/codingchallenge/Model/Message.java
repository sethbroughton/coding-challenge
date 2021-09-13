package com.newrelic.codingchallenge.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Message {

    private static final String REGEX = "^\\d{9}$";
    private String number;
    private boolean isTerminate;

    public Message(String number) {
        if(number.equals("terminate")){
            this.isTerminate = true;
        }
            this.number = number;
    }

    public String getNumber() {
        return this.number;
    }

    public boolean isValid() {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(number);
        return matcher.find();
    }

    public boolean isTerminate() {
        return this.isTerminate;
    }

}
