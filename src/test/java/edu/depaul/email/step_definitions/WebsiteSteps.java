package edu.depaul.email.step_definitions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import edu.depaul.email.PageCrawler;
import edu.depaul.email.StorageService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.HashSet;
import java.util.Set;

public class WebsiteSteps {

    private String website;
    private int emailCount;
    private int goodLinkCount;
    private int badLinkCount;
    private String strEmail;


    @Given("The individual is interested in website: {string}")
    public void the_individual_is_interested_in_website(String site) {
        website = site;
    }

    @Given("The individual is expecting {int} emails")
    public void the_individual_expected_emails(int email) {
        emailCount = email;
    }

    @Given("The individual is expecting {int} good-links and {int} bad-links")
    public void the_individual_expected_links(int goodLink, int badLink) {
        goodLinkCount = goodLink;
        badLinkCount = badLink;
    }

    @Given ("The individual is looking for email: {string}")
    public void the_individual_looking_email(String email){strEmail = email;}

    @Then("The individual is {string}")
    public void the_individual_accuracy(String accuracy){
        StorageService storage = mock(StorageService.class);
        PageCrawler crawler = new PageCrawler(storage);
        crawler.crawl(website);
        crawler.report();
        int realEmailCount = crawler.getEmails().size();
        int realBadLink = crawler.getBadLinks().size();
        int realGoodLink = crawler.getGoodLinks().size();

        if (accuracy.equals("correct")){
            assertTrue(realEmailCount == emailCount && realBadLink == badLinkCount && realGoodLink == goodLinkCount);
        }
        else{
            assertFalse(realEmailCount == emailCount && realBadLink == badLinkCount && realGoodLink == goodLinkCount);
        }
    }

    @Then("The individual {string} find it")
    public void the_individual_email_search (String accuracy){
        StorageService storage = mock(StorageService.class);
        PageCrawler crawler = new PageCrawler(storage);
        crawler.crawl(website);
        crawler.report();
        Set<String> foundEmails = crawler.getEmails();
        if (accuracy.equals("does")){
            assertTrue(foundEmails.contains(strEmail));
        }
        else{
            assertFalse(foundEmails.contains(strEmail));
        }
    }
}
