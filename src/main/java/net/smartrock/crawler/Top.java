package net.smartrock.crawler;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * The programs MAIN entry point
 * <p>
 * Created by chris on 05/07/2016.
 */
public class Top {
    // max pages is currently hard coded -
    // todo this should be configurable
    static final int MAX_PAGES = 50;

    public static void main(String[] args) {
        if (args.length > 0) {
            String startUrl = args[0];

            try {
                // ensure is valid
                URL url = new URL(startUrl);
            } catch (MalformedURLException e) {
                System.out.println("invalid URL( " + startUrl + " ) please try again");
            }

            // create processor and define the "Top" url
            Processor processor = new Processor(startUrl);

            // kick start the requests
            processor.requestUrl(startUrl);

            // process request
            processor.process(MAX_PAGES);
            // when it returns it has completed the crawl

            // todo move to configurable class for output
            StringBuffer stringBuffer = new StringBuffer();
            processor.getSiteMap(stringBuffer);
            System.out.println("===== Completed =====");
            System.out.println(stringBuffer.toString());
        } else {
            // print usage
            System.out.println("Usage is: crawler <url>");
            System.out.println("Example: crawler http://www.bbc.co.uk");
        }
    }
}
