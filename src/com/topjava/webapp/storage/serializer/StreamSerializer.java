package com.topjava.webapp.storage.serializer;

import com.topjava.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializer {

    void writeResume(OutputStream os, Resume resume) throws IOException;

    Resume readResume(InputStream is) throws IOException;
}
