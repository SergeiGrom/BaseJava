package com.topjava.webapp.model;

import java.util.Objects;

public class Contact {
    private final ContactType type;
    private final String link;

    public Contact(ContactType type, String link) {
        Objects.requireNonNull(type, "Contact type must not be null");
        this.type = type;
        this.link = link;
    }

    public ContactType getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return type + ", " + link;
    }
}
