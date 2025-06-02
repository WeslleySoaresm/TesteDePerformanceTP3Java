package org.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio8PUTAtualizar {

    public static void main(String[] args) {
        int entityId = 11;
        String updateJson = "{\"name\": \"atualizado\"}";

        try {
            // 1. PUT para atualizar a entidade
            URL putUrl = new URL("https://apichallenges.eviltester.com/sim/entities/" + entityId);
            HttpURLConnection putConnection = (HttpURLConnection) putUrl.openConnection();

            putConnection.setRequestMethod("PUT");
            putConnection.setRequestProperty("Content-Type", "application/json");
            putConnection.setDoOutput(true);

            DataOutputStream outputStream = new DataOutputStream(putConnection.getOutputStream());
            outputStream.writeBytes(updateJson);
            outputStream.flush();
            outputStream.close();

            int putStatus = putConnection.getResponseCode();
            System.out.println("PUT - Código de status HTTP: " + putStatus);

            BufferedReader putReader = new BufferedReader(
                    new InputStreamReader(putConnection.getInputStream())
            );
            String line;
            StringBuilder putResponse = new StringBuilder();
            while ((line = putReader.readLine()) != null) {
                putResponse.append(line);
            }
            putReader.close();
            System.out.println("PUT - Corpo da resposta:");
            System.out.println(putResponse.toString());

            putConnection.disconnect();

            // 2. GET para verificar atualização
            URL getUrl = new URL("https://apichallenges.eviltester.com/sim/entities/" + entityId);
            HttpURLConnection getConnection = (HttpURLConnection) getUrl.openConnection();
            getConnection.setRequestMethod("GET");

            int getStatus = getConnection.getResponseCode();
            System.out.println("\nGET - Código de status HTTP: " + getStatus);

            BufferedReader getReader = new BufferedReader(
                    new InputStreamReader(getConnection.getInputStream())
            );
            StringBuilder getResponse = new StringBuilder();
            while ((line = getReader.readLine()) != null) {
                getResponse.append(line);
            }
            getReader.close();
            getConnection.disconnect();

            System.out.println("GET - Corpo da resposta:");
            System.out.println(getResponse.toString());

        } catch (Exception e) {
            System.out.println("Erro ao executar PUT ou GET: " + e.getMessage());
        }
    }
}
