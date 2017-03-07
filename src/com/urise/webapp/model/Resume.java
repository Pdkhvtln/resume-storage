package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * com.urise.webapp.model.com.urise.webapp.model.Resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;

    // Unique identifier
    private String uuid;
    private String fullName;

    public Resume(String uuid, String fullName, Map<ContactType, String> contacts, Map<SectionType, Section> sections) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.contacts = contacts;
        this.sections = sections;
    }

    Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid most not be null");
        Objects.requireNonNull(fullName, "fullName most not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume() {
    }

    public static boolean isResume(Resume r) {
        return ((r != null) && (r.uuid != null));
    }

    @Override
    public String toString() {
        return "Uuid: " + uuid + ", full name: " + fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }


    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = this.fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : this.uuid.compareTo(o.uuid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public void addContact(ContactType type, String value) {
        contacts.put(type, value);
    }

    public void addSection(SectionType type, Section section) {
        sections.put(type, section);
    }
}
