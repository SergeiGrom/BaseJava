package com.topjava.webapp.model;

public class Contact {
    private String type;
    private String body;
    private String link;

    public Contact(String body) {
        this.body = body;
    }

    public Contact(String type, String body) {
        this.type = type;
        this.body = body;
    }

    public Contact(String type, String body, String link) {
        this.type = type;
        this.body = body;
        this.link = link;
    }
}
