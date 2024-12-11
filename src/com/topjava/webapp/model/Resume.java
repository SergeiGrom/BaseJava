package com.topjava.webapp.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {
    // Unique identifier
    private final String uuid;
    private final String fullName;

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "UUID must not be null");
        Objects.requireNonNull(fullName, "Full_Name must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(String fullName) {
        Objects.requireNonNull(fullName, "Full_Name must not be null");
        this.uuid = UUID.randomUUID().toString();
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "uuid: " + uuid + ", " + "full_name: " + fullName;
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
