package com.topjava.webapp.model;

import java.util.List;

public class Section {
    SectionType sectionType;
    List<InfField> infFields;

    public Section(SectionType sectionType) {
        this.sectionType = sectionType;
    }

    public void addBlock (InfField block) {
        this.infFields.add(block);
    }
}
