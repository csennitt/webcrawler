package net.smartrock.crawler;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to hold the page details ( title and url), a list of all internal and external links
 * <p>
 * <p>
 * Created by chris on 05/07/2016.
 */
public class Page {
    final private String url;
    final private String title;
    final private List<String> links;
    final private List<String> extLinks;

    /**
     * constructor
     * uses page parser to obtain the links etc
     * The constructor also decides if the page links are local or external
     *
     * @param pageParser
     * @param topHost
     */
    public Page(PageParser pageParser, String topHost) {
        this.url = pageParser.getUrl();
        this.title = pageParser.getTitle();
        this.links = new ArrayList<>();
        this.extLinks = new ArrayList<>();

        for (String link : pageParser.getLinks()) {
            String linkHost = Utils.getHost(link);
            if (linkHost != null) {
                if (linkHost.contains(topHost)) {
                    this.links.add(link);
                } else {
                    this.extLinks.add(link);
                }
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLinks() {
        // todo clone instead
        return links;
    }

    public List<String> getExtLinks() {
        // todo clone instead
        return extLinks;
    }
}
