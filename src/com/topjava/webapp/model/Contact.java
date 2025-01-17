package com.topjava.webapp.model;

import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
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
        return type + " " + link;
    }
}
