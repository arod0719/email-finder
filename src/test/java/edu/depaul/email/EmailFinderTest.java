
package edu.depaul.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmailFinderTest {
    @Test
    @DisplayName("Valid URL with limit")
    void ValidURLTest() {
        EmailFinder finder = new EmailFinder();
        String[] url = {"https://alextest333.htmlsave.net", "5"};
        finder.main(url);
        assertNotNull(finder);
    }

    @Test
    @DisplayName("Blank string passed")
    void emptyArgs(){
        EmailFinder finder = new EmailFinder();
        String[] url = {""};
        finder.main(url);
        assertNotNull(finder);
    }
}
