
package edu.depaul.email;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.Set;

public class EmailFinderTest {


    @Test
    @DisplayName("Constructor should throw an exception when an incorrect url is given")
    void testBadURL() {
        PageFetcher test = new PageFetcher();
        assertThrows(EmailFinderException.class, () -> test.getString("doesNotExist"));
    }

    @Test
    @DisplayName("A test to ensure pageParser works properly")
    void testPageParser(){
        String html = "<html><a href='/some/other/file.html'>my link</a></body></html>";
        Document doc = Jsoup.parse(html);
        PageParser parser = new PageParser();
        parser.findLinks(doc);
        assertEquals(null,doc.getElementById("my link"));
    }

}
