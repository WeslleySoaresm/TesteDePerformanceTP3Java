package org.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleApiPOSTCreateItem {
    public static void main(String[] args) {
        String isbn = SimpleApiGETRandomISBN.getRandomISBN();
        if (isbn == null) {
            System.out.println("ISBN inválido, abortando criação.");
            return;
        }

        try {
            URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonBody = String.format(
                    "{\"isbn\": \"%s\", \"title\": \"Livro de Teste\", \"author\": \"Aluno\"}", isbn);

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(jsonBody);
            out.flush();
            out.close();

            int status = conn.getResponseCode();
            System.out.println("POST criar item - Status: " + status);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("POST criar item - Resposta:");
            System.out.println(response.toString());

            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Erro POST criar item: " + e.getMessage());
        }
    }
}

