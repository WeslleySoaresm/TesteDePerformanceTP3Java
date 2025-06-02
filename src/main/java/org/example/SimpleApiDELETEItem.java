package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleApiDELETEItem {
    public static void main(String[] args) {
        // Use o ISBN do item criado ou outro válido para remover
        String isbn = "80-8-84-266450-4";

        try {
            URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items/" + isbn);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");

            int status = conn.getResponseCode();
            System.out.println("DELETE item - Status: " + status);

            BufferedReader reader;
            if (status >= 400) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }

            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("DELETE item - Resposta:");
            System.out.println(response.toString());

            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Erro DELETE item: " + e.getMessage());
        }
    }
}
