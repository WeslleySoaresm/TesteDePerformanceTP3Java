package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleApiGETRandomISBN {
    public static String getRandomISBN() {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/simpleapi/randomisbn");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int status = conn.getResponseCode();
            System.out.println("GET ISBN aleatório - Status: " + status);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String isbn = reader.readLine();  // a resposta é só o ISBN
            reader.close();
            conn.disconnect();

            System.out.println("ISBN gerado: " + isbn);
            return isbn;

        } catch (Exception e) {
            System.out.println("Erro GET ISBN aleatório: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        getRandomISBN();
    }
}
