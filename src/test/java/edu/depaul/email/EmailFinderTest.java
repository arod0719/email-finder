
package edu.depaul.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class EmailFinderTest {


    @Test
    @DisplayName("Tests StorageService properly implements AddLocation")
    void testStorageAddLocation() {
        StorageService storage = new StorageService();
        StorageService email = storage.addLocation(StorageService.StorageType.EMAIL, "testemail1@gmail.com");
        assertEquals(storage, email);
    }

    @Test
    @DisplayName("Tests StorageService throws an exception when empty List")
    void testStorageWriteListNull() {
        StorageService storage = new StorageService();
        Set<String> emailList = new HashSet<String>();
        assertThrows(EmailFinderException.class, () -> storage.storeList(StorageService.StorageType.EMAIL, emailList));
    }

    @Test
    @DisplayName("Constructor should throw an exception when an incorrect url is given")
    void testBadURL() {
        PageFetcher test = new PageFetcher();
        assertThrows(EmailFinderException.class, () -> test.getString("doesNotExist"));
    }

    @Test
    @DisplayName("Test pageParser finds real emails")
    void testPageParserEmail(){
        Document doc = new PageFetcher().get(System.getProperty("user.dir") + "/src/test/resources/test.html");
        PageParser parser = new PageParser();
        Set<String> emails = parser.findEmails(doc);
        assertTrue(emails.contains("testemail2@yahoo.com"));
    }

    @Test
    @DisplayName("Test pageParser finds real links")
    void testPageParserLinks(){
        Document doc = new PageFetcher().get(System.getProperty("user.dir") + "/src/test/resources/test.html");
        PageParser parser = new PageParser();
        Set<String> links = parser.findLinks(doc);
        assertTrue(links.contains("https://www.twitter.com"));
    }

    @Test
    @DisplayName("A test to ensure pageParser works properly")
    void testPageParserNoLinks(){
        String html = "<html><a href='/some/other/file.html'>my link</a></body></html>";
        Document doc = Jsoup.parse(html);
        PageParser parser = new PageParser();
        parser.findLinks(doc);
        assertEquals(null,doc.getElementById("my link"));
    }

    @Test
    @Disabled
    @DisplayName("A test to ensure pageCrawler works properly")
    void testPageCrawler() throws IOException {
        StorageService storage = new StorageService();
        PageCrawler crawler = new PageCrawler(storage);
        crawler.crawl("https://www.cdm.depaul.edu");
        crawler.report();
        String fileName = "/target/email.txt";
        Path path = Paths.get(fileName);
        byte[] bytes = Files.readAllBytes(path);
        assertNotNull(bytes);
    }

    @Test
    @Disabled
    @DisplayName("Tests StorageService with Valid List")
    void testStorageWriteListValid() {
        StorageService storage = new StorageService();
        Set<String> emailList = new HashSet<String>();
        emailList.add("testEmail1@gmail.com");
        emailList.add("testEmail2@gmail.com");
        storage.storeList(StorageService.StorageType.EMAIL, emailList);
        //assert ?
    }

}
