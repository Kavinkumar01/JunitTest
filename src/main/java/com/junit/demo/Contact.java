package com.junit.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(){

    }

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public boolean validateFirstName() throws Exception {
        if(this.firstName==null || this.firstName.isBlank()){
            throw new Exception("The firstName should have a value");
        }
        return true;
    }

    public boolean validateLastName() throws Exception {
        if(this.lastName==null || this.lastName.isBlank()){
            throw new Exception("The lastName should have a value");
        }
        return true;
    }

    public boolean validatePhoneNumber() throws Exception {
        if(this.phoneNumber.isBlank()){
            throw new Exception("The phoneNumber should have a value");
        }
        if(this.phoneNumber.length()!=10){
            throw new Exception("The phoneNumber should have at least 10 numbers");
        }
        if(!(this.phoneNumber.startsWith("8") || this.phoneNumber.startsWith("9"))){
            throw new Exception("The phoneNumber should start with 8 or 9 numbers");
        }
//        Pattern pattern= Pattern.compile("\\d+");
//        Matcher matcher=pattern.matcher(this.phoneNumber);
//        if(matcher.find()){
//            throw new Exception("The phoneNumber should contain only digits");
//        }
        return true;
    }
}
