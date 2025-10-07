package com.silver.web.surfer.http.util;

import com.silver.web.surfer.http.config.HttpConfigurationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadingFileUtils {


    public static String loadHtml(String path){
        String html= "";

        FileReader flr = null;
        try {
            flr = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer strBuffer = new StringBuffer();
        int i;
        while(true) {
            try {
                if ((i = flr.read()) == -1) break;
            } catch (IOException e) {
                throw new HttpConfigurationException(e);
            }
            strBuffer.append((char) i);
        }
        html = strBuffer.toString();
        return html;
    }

}
