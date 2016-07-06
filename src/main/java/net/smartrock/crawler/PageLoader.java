package net.smartrock.crawler;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by chris on 05/07/2016.
 */
public class PageLoader {
    static public Page load( String url, String topHost ) throws IOException {
        URL targetUrl = new URL( url );
        URLConnection connection = targetUrl.openConnection();
        InputStream inputStream = connection.getInputStream();
        String encoding = connection.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;
        String body = IOUtils.toString(inputStream, encoding);
        inputStream.close();

        PageParser pageParser = new SimplePageParser( url, body );
        Page page = new Page( pageParser, topHost  );
        return page;
    }
}
