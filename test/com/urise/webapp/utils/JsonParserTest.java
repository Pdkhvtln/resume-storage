package com.urise.webapp.utils;

import com.urise.webapp.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.util.UUID;

import static com.urise.webapp.TestData.*;
import static org.junit.Assert.*;

/**
 * Created by andrew on 15.03.17.
 */
public class JsonParserTest {
    @Test
    public void read() throws Exception {
        String json = JsonParser.write(RESUME_1);
        System.out.println(json);
        Resume r = JsonParser.read(json, Resume.class);
        Assert.assertEquals(RESUME_1, r);

    }


    @Test
    public void write() throws Exception {
        Section section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1,Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json,Section.class);
                Assert.assertEquals(section1,section2);
    }

}