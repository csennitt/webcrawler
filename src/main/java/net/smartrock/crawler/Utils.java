package net.smartrock.crawler;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;


/**
 * Created by chris on 05/07/2016.
 */

public class Utils {

    /**
     * Test Helper only
     *
     * @param filename
     * @return
     */
    static public String readFileFromResource( String filename ) {
        ClassLoader classLoader = Utils.class.getClassLoader();
        File theFile = new File( classLoader.getResource( filename ).getFile() );
        String contents = "";
        try {
            contents = FileUtils.readFileToString( theFile, Charset.forName("utf-8") );
        } catch (IOException e) {
            contents = "";
        }
        return contents;
    }

    /**
     * Test Helper only
     *
     * @param list
     * @param target
     * @return
     */
    static public boolean listContains(List<String> list, String target ) {
        for( String item : list ) {
            if( item.equals( target ) ) return true;
        }
        return false;
    }

    /**
     * calculate a host name for a url - if the url is invalid returns null
     * @param url
     * @return
     */

    static public String getHost( String url ) {
        try {
            URL hostUrl = new URL( url );
            return hostUrl.getHost();
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
