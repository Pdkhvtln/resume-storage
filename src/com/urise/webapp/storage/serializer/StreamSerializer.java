package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by andrew on 07.03.17.
 */
public interface StreamSerializer {
    void doWrite(Resume r, OutputStream os) throws IOException;
    Resume doRead (InputStream is) throws IOException;
}
