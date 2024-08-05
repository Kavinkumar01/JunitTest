package com.junit.demo;


import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



public class ContactManager {
    Map<String,Contact> contactList=new ConcurrentHashMap<>();

    public String addContact(String firstName,String lastName, String phoneNumber) throws Exception {
        String output="";
        try {
            Contact newContact = new Contact(firstName, lastName, phoneNumber);
            if (validateContact(newContact) && checkIfContactAlreadyExist(newContact)) {
                contactList.put(generateKey(newContact),newContact);
                output= String.format("contact for %s and %s was created successfully", firstName, lastName);
            }
        }catch (Exception e) {
            output= "There was an error: "+e;
            System.out.println(output);
            throw new Exception(e);
        }
        return output;
    }

    public Collection<Contact> getAllContact(){

        System.out.println("The size of the list is :"+this.contactList.values().size());
        return this.contactList.values();
    }

    private boolean checkIfContactAlreadyExist(Contact newContact) throws Exception {
        if(contactList.containsKey(generateKey(newContact))){
            throw new Exception("Contact Already Exists");
        }
        return true;
    }

    private boolean validateContact(Contact newContact) throws Exception {
        if(newContact.validateFirstName() && newContact.validateLastName() && newContact.validatePhoneNumber()){
            return true;
        }else{
            return false;
        }
    }

    public String generateKey(Contact contact){
        return String.format("%s-%s",contact.getFirstName(),contact.getLastName());
    }

}
