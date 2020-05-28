
package edu.depaul.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ListWriterTest {
    @Test
    @DisplayName("Constructor correctly writes to files")
    void constructorTest() throws IOException {
        OutputStream stream = new FileOutputStream("OutputTest.txt");
        ListWriter writer = new ListWriter(stream);
        Collection<String> collection = new ArrayList<>();
        String teststr = "testing testing testing";
        collection.add(teststr);
        writer.writeList(collection);

        Set<String> lines = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader("OutputTest.txt"));
        String line = reader.readLine();
        while (line != null) {
            lines.add(line);
            line = reader.readLine();
        }
        assertTrue(lines.contains(teststr));
    }
}

