package net.smartrock.crawler;

import java.io.IOException;
import java.util.*;

/**
 * single threaded implementation
 * <p>
 * This class is the main processing engine
 * Once instantiated with the top URL it parses that url and requests all internal links to be processed
 * <p>
 * A Queue has been used to allow for future multi threading version(s)
 * This class consumes the request queue until its empty
 * <p>
 * Created by chris on 05/07/2016.
 */
public class Processor {

    final private String topHost;
    final String lineFeed = System.getProperty("line.separator");

    // unProcessed are those not yet loaded into pages
    final UrlTracker urlTracker = new UrlTracker();

    final Queue<String> requestQueue = urlTracker.getRequestQueue();

    // all completed pages
    final private HashMap<String, Page> pages = new HashMap<>();

    public Processor(String topUrl) {
        this.topHost = Utils.getHost(topUrl);
    }

    /**
     * Read from the requests queue and process until it is empty OR the max pages has been reached
     * <p>
     * Note: We know we are done when the request queue is empty
     *
     * @param maxPages
     */

    public void process(int maxPages) {
        //

        // while there is a url to process
        while (!requestQueue.isEmpty() && pages.size() < maxPages) {
            String url = requestQueue.poll();
            System.out.println("Processing: " + url);
            if (url != null) {
                // then we got something so process it
                Page page = null;
                try {
                    page = PageLoader.load(url, topHost);
                    urlTracker.add(page.getLinks());
                    pages.put(url, page);

                } catch (IOException e) {
                    // todo
                }
            }
        }
    }

    /**
     * Simple formatted output
     * <p>
     * todo abstract this and plug in a formatter
     *
     * @param sb
     */
    public void getSiteMap(StringBuffer sb) {
        // getKeys from hasmap of pages
        Set<String> keys = pages.keySet();
        for (String pageKey : keys) {
            Page page = pages.get(pageKey);
            sb.append("Web Page: " + page.getUrl());
            sb.append(lineFeed);
            sb.append("    Title: " + page.getTitle());
            sb.append(lineFeed);

            sb.append("    Links: ");
            sb.append(lineFeed);
            for (String link : page.getLinks()) {
                sb.append("        " + link);
                sb.append(lineFeed);
            }
            sb.append("    External Links: ");
            sb.append(lineFeed);
            for (String link : page.getExtLinks()) {
                sb.append("        " + link);
                sb.append(lineFeed);
            }
        }
    }

    /**
     * request that a url is processed
     *
     * @param url
     */
    public void requestUrl(String url) {
        urlTracker.add(url);
    }

}
