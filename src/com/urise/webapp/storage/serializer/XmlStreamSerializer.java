package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;
import com.urise.webapp.utils.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by andrew on 07.03.17.
 */
public class XmlStreamSerializer implements StreamSerializer {
    private XmlParser xmlParser;

    public XmlStreamSerializer() {
        xmlParser = new XmlParser(
                Resume.class, Organization.class, Link.class,
                OrganizationSection.class, TextSection.class,
                ListSection.class, Organization.Position.class
        );
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(r, w);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }
}
