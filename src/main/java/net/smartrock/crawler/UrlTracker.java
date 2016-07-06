package net.smartrock.crawler;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * class to hold track unProcessed urls
 *
 * Created by chris on 05/07/2016.
 */
public class UrlTracker {

    // all contains all URL's seen
    final private Set<String> all = new HashSet<>();
    final private Set<String> completed = new HashSet<String>();

    final private Queue<String> requested = new ConcurrentLinkedQueue<>();

    public boolean isComplete() {
        return all.size() == completed.size();
    }

    public void add( List<String> urlList ) {
        for( String url : urlList ) {
            add( url );
        }

    }
    synchronized public void add( String url ) {
        if( !all.contains( url ) ) {
            // add to BOTH all and requested queue
            all.add(  url );
            requested.add( url );
        }
    }

    synchronized public void completed( String url ) {
        completed.add( url );
    }

    public Queue<String> getRequestQueue() {
        return requested;
    }

}