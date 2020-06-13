package stackjava.com.mongodb.testFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class testFile {
    public static void main(String[] args) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("https://017shop.ca/products.json?limit=1"));

            System.out.println("Đọc nội dung file sử dụng phương thức readLine()");

            String textInALine;

            while ((textInALine = br.readLine()) != null) {
                System.out.println(textInALine);
                textInALine = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
