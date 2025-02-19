package com.topjava.webapp.storage.serializer;

import com.topjava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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
            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            // write sections
            Map<SectionType, Section> sections = resume.getSections();
            writeCollection(dos, sections.entrySet(), entry -> writeSection(entry, dos));
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
            readItems(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            // read sections
            readItems(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }

    }

    private void writeSection(Map.Entry<SectionType, Section> entry, DataOutputStream dos) throws IOException {
        SectionType sectionType = entry.getKey();
        Section section = entry.getValue();
        dos.writeUTF(sectionType.name());
        switch (sectionType) {
            case OBJECTIVE, PERSONAL -> dos.writeUTF(((TextSection) section).getContent());
            case ACHIEVEMENT, QUALIFICATIONS -> writeCollection(dos, ((ListSection) section).getItems(), dos::writeUTF);
            case EXPERIENCE, EDUCATION -> writeCollection(dos, ((CompanySection) section).getCompanies(), company -> {
                dos.writeUTF(company.getHomePage().getName());
                dos.writeUTF(company.getHomePage().getWebsite());
                writeCollection(dos, company.getPeriods(), period -> {
                    writeLocalDate(dos, period.getStartDate());
                    writeLocalDate(dos, period.getEndDate());
                    dos.writeUTF(period.getTitle());
                    dos.writeUTF(period.getDescription() != null ? period.getDescription() : "");
                });
            });
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case OBJECTIVE, PERSONAL -> {
                return new TextSection(dis.readUTF());
            }
            case ACHIEVEMENT, QUALIFICATIONS -> {
                return new ListSection(readList(dis, dis::readUTF));
            }
            case EXPERIENCE, EDUCATION -> {
                return new CompanySection(
                        readList(dis, () -> new Company(
                                new Link(dis.readUTF(), dis.readUTF()),
                                readList(dis, () -> new Company.Period(
                                        readLocalDate(dis), readLocalDate(dis), dis.readUTF(), readDescription(dis)))
                        ))
                );
            }
            default -> throw new IllegalStateException();
        }
    }

    @FunctionalInterface
    private interface WriteItems<T> {
        void doWrite(T t) throws IOException;
    }

    @FunctionalInterface
    private interface ReadItems<T> {
        T doRead() throws IOException;
    }

    @FunctionalInterface
    private interface ProcessingItems {
        void doProcess() throws IOException;
    }

    private void readItems(DataInputStream dis, ProcessingItems process) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            process.doProcess();
        }
    }

    private <T> List<T> readList(DataInputStream dis, ReadItems<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.doRead());
        }
        return list;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, WriteItems<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.doWrite(item);
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate date) throws IOException {
        dos.writeInt(date.getYear());
        dos.writeInt(date.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private String readDescription(DataInputStream dis) throws IOException {
        String text = dis.readUTF();
        return !text.isEmpty() ? text : null;

    }

}
