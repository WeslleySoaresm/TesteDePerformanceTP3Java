package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleApiGETAllItems {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int status = conn.getResponseCode();
            System.out.println("GET todos os itens - Status: " + status);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("GET todos os itens - Resposta:");
            System.out.println(response.toString());

            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Erro GET todos os itens: " + e.getMessage());
        }
    }
}
