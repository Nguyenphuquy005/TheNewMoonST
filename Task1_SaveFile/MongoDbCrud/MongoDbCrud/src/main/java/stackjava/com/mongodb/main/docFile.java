package stackjava.com.mongodb.main;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class docFile {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("D:\\TheNewMoonST\\file\\shopify.txt"));
            String contentLine = br.readLine();
            while (contentLine != null) {

                if (checkValid(contentLine)){
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(contentLine);
                    SaveFile(contentLine);
                }
                contentLine = br.readLine();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean checkValid( String domain) throws IOException {
        try {
            URL url = new URL("https://"+ domain +"/products.json?limit=1");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
            if (code==200){
                return true;
            }
        }
        catch (ConnectException | UnknownHostException| SSLException e){

        }
        return false;
    }
    public  static void  SaveFile(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\TheNewMoonST\\file\\shopify_link.txt", true));
        writer.write(data + "\n");
        writer.close();
    }
}
