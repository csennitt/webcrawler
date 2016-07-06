package net.smartrock.crawler;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by chris on 06/07/2016.
 */
public class PageTest {
    final String TEST_URL_BBC = "http://www.bbc.co.uk";
    final String TEST_TITLE = "This is the title :)";
    private Page page;

    @Before
    public void setup() {
        String contents = Utils.readFileFromResource( "test.html" );
        SimplePageParser simplePageParser = new SimplePageParser(TEST_URL_BBC, contents );
        page = new Page( simplePageParser, Utils.getHost( TEST_URL_BBC ));

    }
    @Test
    public void getUrlWorks() {
        assertTrue( page.getUrl().equals( TEST_URL_BBC ));
    }

    @Test
    public void getTitleWorks()  {
        assertTrue( page.getTitle().equals( TEST_TITLE ));
    }

    @Test
    public void getLinksWorks() throws Exception {
        assertTrue( page.getLinks().size() == 2 );
    }

    @Test
    public void getExtLinks() throws Exception {
        assertTrue( page.getExtLinks().size() == 3 );
    }

}