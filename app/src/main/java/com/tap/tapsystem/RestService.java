package com.tap.tapsystem;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Lenovo ThinkPad X220 on 9/12/2016.
 */
public class RestService {

    public String getString(String uri){
        String result;
        try{
            URL url = new URL(uri.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine())!=null)
            {
                sb.append(line);
            }
            br.close();
            result = sb.toString();
        }
        catch(Exception ex){
            result = ex.getMessage();
        }
        return result;
    }

}
