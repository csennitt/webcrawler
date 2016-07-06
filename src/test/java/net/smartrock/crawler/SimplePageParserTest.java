package net.smartrock.crawler;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static net.smartrock.crawler.Utils.listContains;
import static org.junit.Assert.*;

/**
 *
 * Created by chris on 05/07/2016.
 */
public class SimplePageParserTest {
    private SimplePageParser simplePage;

    final String TEST_LINK_GOOGLE = "http://www.google.com";
    final String TEST_LINK_BBC = "http://www.bbc.co.uk";
    final String TEST_LINK_ODDONE = "http://www.oddOne.com";
    final String TEST_LINK_ODDTWO = "http://www.oddTwo.com";

    final String TEST_URL = "Test";

    final String TEST_TITLE = "This is the title :)";

    @Before
    public void setUp() throws Exception {
        String contents = Utils.readFileFromResource( "test.html" );
        simplePage = new SimplePageParser( TEST_URL, contents );
    }

    @Test
    public void getTitleWorks() {
        String title = simplePage.getTitle();
        assertTrue( title.contentEquals( TEST_TITLE ) );
    }

    @Test
    public void getLinksWorksWithSingleQuote() {
        List<String> links = simplePage.getLinks();
        assertTrue("Links should contain: " + TEST_LINK_GOOGLE, listContains(links, TEST_LINK_GOOGLE));
    }
    @Test
    public void getLinksWorksWithDoubleQuote() {
        List<String> links = simplePage.getLinks();
        assertTrue( "Links should contain: " + TEST_LINK_BBC, listContains( links, TEST_LINK_BBC ) );

    }
    @Test
    public void getLinksWorksWithLeadingWhiteSPace() {
        List<String> links = simplePage.getLinks();
        assertTrue( "Links should contain: " + TEST_LINK_ODDONE, listContains( links, TEST_LINK_ODDONE ) );

    }
    @Test
    public void getLinksWorksWithLeadingAndTrailingWhiteSPace() {
        List<String> links = simplePage.getLinks();
        assertTrue( "Links should contain: " + TEST_LINK_ODDTWO, listContains( links, TEST_LINK_ODDTWO ) );

    }
    @Test
    public void getUrlWorks() {
        String url = simplePage.getUrl();
        assertTrue( "Url must be: " + TEST_URL, url.equals( TEST_URL) );

    }

}