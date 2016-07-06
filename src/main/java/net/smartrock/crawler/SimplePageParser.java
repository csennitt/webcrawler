package net.smartrock.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * quick implementation of PageParser using regex
 * regex is NOT the recommended way to parse HTML - should be good enough for first attempt
 *
 * Created by chris on 05/07/2016.
 */

public class SimplePageParser implements PageParser {
    final static String TITLE_EXP = "<title>(.*?)</title>";
    final static String Link_EXP = "href\\s*=\\s*[\"|'](.*?)[\"|']";

    final static Pattern LinkPattern = Pattern.compile( Link_EXP );
    final static Pattern TitlePattern = Pattern.compile( TITLE_EXP );

    final private String url;
    final private String title;
    final private List<String> links;

    public SimplePageParser(String url, String contents ) {
        this.url = url;
        this.title = calcTitle( contents );
        this.links = calcLinks( contents );
    }

    private List<String> calcLinks( String contents ) {
        final ArrayList<String> links = new ArrayList<>();
        Matcher matcher = LinkPattern.matcher( contents );
        while (matcher.find()) {
            String item = matcher.group(1);
            links.add( item  );
        }
        return links;
    }

    private String calcTitle( String contents ) {
        Matcher matcher = TitlePattern.matcher( contents );
        if( matcher.find() ) return matcher.group(1);
        return "No Title";
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public List<String> getLinks() {
        return this.links;
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}