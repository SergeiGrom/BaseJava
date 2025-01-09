package com.topjava.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume {
    // Unique identifier
    private final String uuid;
    private final String fullName;
    private final Map<ContactType, Contact> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "UUID must not be null");
        Objects.requireNonNull(fullName, "Full_Name must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(String fullName) {
        Objects.requireNonNull(fullName, "Full_Name must not be null");
        this.fullName = fullName;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void getContact(ContactType contactType) {
        contacts.get(contactType);
    }

    public void getSection(SectionType sectionType) {
        sections.get(sectionType);
    }

    public void addContact(Contact contact) {
        Objects.requireNonNull(contact, "Contact must not be null");
        contacts.put(contact.getType(), contact);
    }

    public void addSection(SectionType type, Section section) {
        Objects.requireNonNull(section, "Section must not be null");
        Objects.requireNonNull(type, "SectionType must not be null");
        sections.put(type, section);
    }

    @Override
    public String toString() {
        return uuid + '\n' +
               fullName + '\n' +
               contacts.values() + '\n' +
               sections.values();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }
}
