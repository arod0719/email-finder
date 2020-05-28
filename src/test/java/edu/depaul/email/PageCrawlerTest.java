
package edu.depaul.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PageCrawlerTest {
    @Test
    @DisplayName("A test to ensure pageCrawler works properly for emails")
    void testPageCrawler() {
        StorageService storage = mock(StorageService.class);
        PageCrawler crawler = new PageCrawler(storage);
        crawler.crawl("https://alextest333.htmlsave.net");
        crawler.report();
        assertTrue(crawler.getEmails().size() == 3);
    }

    @Test
    @DisplayName("Tests that page crawler works for good links")
    void testGoodLink() throws IOException {
        StorageService storage = mock(StorageService.class);
        PageCrawler crawler = new PageCrawler(storage);
        String url= "https://alextest333.htmlsave.net";
        crawler.crawl(url);
        crawler.report();
        assertEquals(2, crawler.getGoodLinks().size());
    }

    @Test
    @DisplayName("Tests that page crawler works for bad links")
    void testBadLink() throws IOException {
        StorageService storage = mock(StorageService.class);
        PageCrawler crawler = new PageCrawler(storage);
        String url= "https://alextest333.htmlsave.net";
        crawler.crawl(url);
        crawler.report();
        assertEquals(2, crawler.getBadLinks().size());
    }

    @Test
    @DisplayName("Tests PageCrawler construction without max emails given.")
    void testConstructorNoMaxEmails() {
        StorageService storage = mock(StorageService.class);
        PageCrawler crawler = new PageCrawler(storage);
        assertNotNull(crawler);
    }

    @Test
    @DisplayName("Tests PageCrawler construction with max emails given.")
    void testConstructorWithMaxEmails() {
        StorageService storage = mock(StorageService.class);
        PageCrawler crawler = new PageCrawler(storage, 20);
        assertNotNull(crawler);
    }

    @Test
    @DisplayName("Tests bad link detection.")
    void testCrawlBadLinks() throws IOException {
        StorageService storage = mock(StorageService.class);
        PageCrawler crawler = new PageCrawler(storage);
        String badurl= "https://www.notrealwebsitejustatest.com/";
        crawler.crawl(badurl);
        crawler.report();
        assertTrue(crawler.getBadLinks().contains(badurl));
    }

    @Test
    @DisplayName("Tests that page crawler stops when max is reached")
    void testCrawlMax() throws IOException {
        StorageService storage = mock(StorageService.class);
        PageCrawler crawler = new PageCrawler(storage, 0);
        String url= "https://alextest333.htmlsave.net";
        crawler.crawl(url);
        crawler.report();
        assertEquals(0, crawler.getEmails().size());
    }
}

