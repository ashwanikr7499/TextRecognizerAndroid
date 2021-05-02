package com.dorvis.textrecognitionandroid;

import java.util.ArrayList;

public class Contact {
    private static int lastContactId = 0;
    private String time;
    private String text;

    public Contact(String time, String text) {
        this.time = time;
        this.text = text;
    }

    public static ArrayList<Contact> createContactsList(int numContacts) {
        ArrayList<Contact> contacts = new ArrayList<Contact>();

        for (int i = 1; i <= numContacts; i++) {
            Long tsLong = System.currentTimeMillis() / 1000;
            String ts = tsLong.toString();
            contacts.add(new Contact(ts, "Hello World" + i));
        }

        return contacts;
    }

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }
}