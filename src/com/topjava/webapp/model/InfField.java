package com.topjava.webapp.model;

import java.util.List;

public class InfField {
    String header;
    String workPosition; //
    String link;
    String startDate;
    String endDate;
    String singleBlock;
    List<String> textBlocks;

    public InfField() {
    }

    public InfField(String singleBlock) {
        this.singleBlock = singleBlock;
    }

    public InfField(List<String> textBlocks) {
        this.textBlocks = textBlocks;
    }

    public InfField(String header, String workPosition, String link, String startDate, String endDate, List<String> textBlocks) {
        this.header = header;
        this.workPosition = workPosition;
        this.link = link;
        this.startDate = startDate;
        this.endDate = endDate;
        this.textBlocks = textBlocks;
    }

    public void addTextBlock(String text) {
        textBlocks.add(text);
    }
}
