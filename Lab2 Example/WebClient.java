package examples;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WebClient {
    
    public static void main(String[] args){
        try{
            URL u;
            HttpURLConnection h;
            InputStream is;
            u = new URL("https://www.kth.se");
            h = (HttpURLConnection)u.openConnection();
            h.setRequestProperty("Cookie", "JSESSIONID=3242342343243;exp");
            //Addera headfält mm innan "submit"
            h.connect();
            //Nu kan vi endast läsa för nu är redan request skickad och endast response finns.
            //h.getHeaderField(name)
            is = h.getInputStream();
            System.out.println(h.getHeaderField("Server"));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str;
            while( (str = br.readLine()) != null){
                System.out.println(str);
            }
        }
        catch(java.net.MalformedURLException e){
            System.out.println(e.getMessage());
        }
        catch(java.io.IOException e){
            System.out.println(e.getMessage());
        }
    }
}
