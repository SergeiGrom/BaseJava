package com.topjava.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private ContactType type;
    private String link;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return type == contact.type && Objects.equals(link, contact.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, link);
    }

    @Override
    public String toString() {
        return type + " " + link;
    }
}
