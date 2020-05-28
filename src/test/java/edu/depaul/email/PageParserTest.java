
package edu.depaul.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PageParserTest {

    @Test
    @DisplayName("Constructor should throw an exception when an incorrect url is passed")
    void testBadURL() {
        PageFetcher test = new PageFetcher();
        assertThrows(EmailFinderException.class, () -> test.getString("fakeurl"));
    }

    @Test
    @DisplayName("Test pageParser finds real emails")
    void testPageParserEmail(){
        Document doc = new PageFetcher().get(System.getProperty("user.dir") + "/src/test/resources/test.html");
        PageParser parser = new PageParser();
        Set<String> emails = parser.findEmails(doc);
        assertTrue(emails.size() == 3);
    }

    @Test
    @DisplayName("Test pageParser finds real links")
    void testPageParserLinks(){
        Document doc = new PageFetcher().get(System.getProperty("user.dir") + "/src/test/resources/test.html");
        PageParser parser = new PageParser();
        Set<String> links = parser.findLinks(doc);
        System.out.println(links);
        assertTrue(links.size() == 3);
    }

    @Test
    @DisplayName("A test to ensure pageParser works properly without links")
    void testPageParserNoLinks(){
        String html = "<html><a href='/some/other/file.html'>my link</a></body></html>";
        Document doc = Jsoup.parse(html);
        PageParser parser = new PageParser();
        parser.findLinks(doc);
        assertEquals(null,doc.getElementById("my link"));
    }


}
