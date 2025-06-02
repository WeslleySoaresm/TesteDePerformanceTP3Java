package org.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleApiPUTUpdateItem {
    public static void main(String[] args) {
        // Use o ISBN gerado na criação ou insira manualmente um ISBN válido
        String isbn = "052-8-37-256805-1";

        try {
            URL url = new URL("https://apichallenges.eviltester.com/simpleapi/items");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonBody = String.format(
                    "{\"isbn\": \"%s\", \"title\": \"Livro Atualizado\", \"weslley\": \"Aluno Atualizado\"}", isbn);

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(jsonBody);
            out.flush();
            out.close();

            int status = conn.getResponseCode();
            System.out.println("PUT atualizar item - Status: " + status);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("PUT atualizar item - Resposta:");
            System.out.println(response.toString());

            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Erro PUT atualizar item: " + e.getMessage());
        }
    }
}
