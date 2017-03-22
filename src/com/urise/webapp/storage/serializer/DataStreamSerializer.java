package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by andrew on 01.03.17.
 */
public class DataStreamSerializer implements StreamSerializer {

    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            //TODO implements sections
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().name());//SectionType

                Section section = entry.getValue();
                String nameClass = section.getClass().getName();
                dos.writeUTF(nameClass);//Section
                if (nameClass.equals("com.urise.webapp.model.TextSection")) {
                    TextSection textSection = (TextSection) section;
                    dos.writeUTF(textSection.getContent());//content
                }

                if (nameClass.equals("com.urise.webapp.model.ListSection")) {
                    ListSection listSection = (ListSection) section;
                    List<String> list = listSection.getItems();
                    dos.writeInt(list.size());
                    for (int i = 0; i < list.size(); i++) {
                        dos.writeUTF(list.get(i));//items
                    }
                }
                if (nameClass.equals("com.urise.webapp.model.OrganizationSection")) {
                    OrganizationSection organizations = (OrganizationSection) section;
                    List<Organization> listOrganizations = organizations.getOrganizations();
                    dos.writeInt(listOrganizations.size());
                    for (int i = 0; i < listOrganizations.size(); i++) {

                        Link homePage = listOrganizations.get(i).getHomePage();
                        dos.writeUTF(homePage.getName());//name
                        dos.writeUTF(homePage.getUrl());//url

                        List<Organization.Position> listPositions = listOrganizations.get(i).getPositions();
                        dos.writeInt(listPositions.size());
                        for (int j = 0; j < listPositions.size(); j++) {
                            Organization.Position position = listPositions.get(j);
                            dos.writeUTF(String.valueOf(position.getStartDate()));
                            dos.writeUTF(String.valueOf(position.getEndDate()));
                            dos.writeUTF(position.getTitle());
                            dos.writeUTF(position.getDescription());
                        }
                    }
                }
            }//end for sections
        }
    }

    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                ContactType contactType = ContactType.valueOf(dis.readUTF());
                String value = dis.readUTF();
                resume.setContact(contactType, value);
            }
            //TODO implements sections
            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());//dos.writeUTF(entry.getKey().getTitle());
                String nameClass = dis.readUTF();//                dos.writeUTF(nameClass);

                Section section = null;
                if (nameClass.equals("com.urise.webapp.model.TextSection")) {
                    section = new TextSection(dis.readUTF());//content
                }
                if (nameClass.equals("com.urise.webapp.model.ListSection")) {
                    int listSectionSize = dis.readInt();//dos.writeInt(list.size());
                    List<String> items = new ArrayList<>();
                    for (int j = 0; j < listSectionSize; j++) {
                        items.add(dis.readUTF());//items
                    }
                    section = new ListSection(items);
                }
                if (nameClass.equals("com.urise.webapp.model.OrganizationSection")) {
                    List<Organization> listOrganizations = new ArrayList<>();
                    int listOrganizationsSize = dis.readInt();
                    for (int j = 0; j < listOrganizationsSize; j++) {
                        String Name = dis.readUTF();
                        String Url = dis.readUTF();
                        Link homePage = new Link(Name, Url);
                        List<Organization.Position> listPositions = new ArrayList<>();
                        int listPositionsSize = dis.readInt();
                        for (int k = 0; k < listPositionsSize; k++) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            formatter = formatter.withLocale(Locale.UK);
                            LocalDate startDate = LocalDate.parse(dis.readUTF(), formatter);
                            LocalDate endDate = LocalDate.parse(dis.readUTF(), formatter);
                            listPositions.add(new Organization.Position(startDate, endDate, dis.readUTF(), dis.readUTF()));
                        }
                        Organization organization = new Organization(homePage, listPositions);
                        listOrganizations.add(organization);
                    }
                    section = new OrganizationSection(listOrganizations);
                }
                resume.setSection(sectionType, section);
            }
            return resume;
        }
    }
}

