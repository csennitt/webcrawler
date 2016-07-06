package net.smartrock.crawler;

import java.util.List;

/**
 * Interface definition for PageParser
 * <p>
 * //todo add getFixed for static/gifs etc
 * <p>
 * Created by chris on 05/07/2016.
 */
public interface PageParser {
    String getUrl();

    String getTitle();

    List<String> getLinks();
}
