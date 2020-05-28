
package edu.depaul.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class StorageServiceTest {
    @Test
    @DisplayName("Storage Service Constructor")
    void constructorTest() {
        StorageService storage = new StorageService();
        assertNotNull(storage);
    }

    @Test
    @DisplayName("Tests how Storage Service handles empty collections")
    void EmptyCollectionTest() {
        StorageService storage = new StorageService();
        Collection<String> collection = new ArrayList<String>();
        assertThrows(EmailFinderException.class, () -> storage.storeList(StorageService.StorageType.GOODLINKS, collection));
    }

    @Test
    @DisplayName("Tests how Storage Service writes to a file")
    void WriteToFile() throws IOException {
        StorageService storage = new StorageService();
        storage.addLocation(StorageService.StorageType.EMAIL, "email.txt");
        Collection<String> collection = new ArrayList<String>();
        String email = "emailtest1@gmail.com";
        collection.add(email);
        storage.storeList(StorageService.StorageType.EMAIL, collection);

        Set<String> lines = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader("email.txt"));
        String line = reader.readLine();
        while (line != null) {
            lines.add(line);
            line = reader.readLine();
        }
        assertTrue(lines.contains(email));
    }
}
