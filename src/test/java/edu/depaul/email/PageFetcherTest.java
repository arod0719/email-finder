
package edu.depaul.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PageFetcherTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/websites.csv", numLinesToSkip = 1)
    void validWebsiteParam(String website, String validity) {
        PageFetcher test = new PageFetcher();
        if (validity.equals("X"))
            assertThrows(EmailFinderException.class, () -> test.getString(website));
        else{
           try{
               test.getString(website);
           }
           catch(Exception e){
               assertTrue(false);
            }
        }
    }

    @Test
    @DisplayName("Page fetcher should throw an exception when an incorrect format url is given")
    void testIncorrectURL() {
        PageFetcher test = new PageFetcher();
        assertThrows(EmailFinderException.class, () -> test.getString("doesNotExist"));
    }

    @Test
    @DisplayName("Page fetcher should throw an exception when an invalid url is given")
    void testBadURL() {
        PageFetcher test = new PageFetcher();
        assertThrows(EmailFinderException.class, () -> test.getString("https://thisWebsiteIsNotRealTest.com"));
    }

    @Test
    @DisplayName("Page fetcher should be able to get a string from a webpage and from a file")
    void testDocumentGet() {
        PageFetcher test = new PageFetcher();
        String strWebsite = test.getString("https://alextest333.htmlsave.net");
        Document strFile = test.get(System.getProperty("user.dir") + "/src/test/resources/test.html");
        assertEquals(strWebsite, strFile.outerHtml());
    }

}
