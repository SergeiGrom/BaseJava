package com.topjava.webapp.storage.serializer;

import com.topjava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void writeResume(OutputStream os, Resume resume) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            // write UUID, FullName
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            // write contacts
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            // write sections
            Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(resume.getSections().size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case OBJECTIVE, PERSONAL -> dos.writeUTF(((TextSection) section).getContent());
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> list = ((ListSection) section).getItems();
                        dos.writeInt(list.size());
                        for (Object item : list) {
                            dos.writeUTF(item.toString());
                        }
                    }
                    case EXPERIENCE, EDUCATION -> {
                        List<Company> companies = ((CompanySection) section).getCompanies();
                        dos.writeInt(companies.size());
                        for (Company company : companies) {
                            dos.writeUTF(company.getHomePage().getName());
                            dos.writeUTF(company.getHomePage().getWebsite());
                            List<Company.Period> periods = company.getPeriods();
                            dos.writeInt(periods.size());
                            for (Company.Period period : company.getPeriods()) {
                                writeLocalDate(dos, period.getStartDate());
                                writeLocalDate(dos, period.getEndDate());
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getDescription() != null ? period.getDescription() : "");
                            }
                        }
                    }
                }
            }

        }
    }

    @Override
    public Resume readResume(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            // read UUID, FullName
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            // read contacts
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            // read sections
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            }

            return resume;
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        Section section = null;
        switch (sectionType) {
            case OBJECTIVE, PERSONAL -> {
                section = new TextSection(dis.readUTF());
            }
            case ACHIEVEMENT, QUALIFICATIONS -> {
                int size = dis.readInt();
                List<String> items = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    items.add(dis.readUTF());
                }
                section = new ListSection(items);
            }
            case EXPERIENCE, EDUCATION -> {
                int size = dis.readInt();
                List<Company> companies = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    Link homePage = new Link(dis.readUTF(), dis.readUTF());
                    int periodsSize = dis.readInt();
                    List<Company.Period> periods = new ArrayList<>(periodsSize);
                    for (int j = 0; j < periodsSize; j++) {
                        periods.add(new Company.Period(readLocalDate(dis), readLocalDate(dis), dis.readUTF(), readDescription(dis)));
                    }
                    companies.add(new Company(homePage, periods));
                }
                section = new CompanySection(companies);
            }
        }
        return section;
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonth().getValue());
    }

    private String readDescription(DataInputStream dis) throws IOException {
        String descr = dis.readUTF();
        return !descr.isEmpty() ? descr : null;
    }

}
