package org.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio7POSTAtualizar {

    public static void main(String[] args) {
        int entityId = 10;
        String updateJson = "{\"name\": \"atualizado\"}";

        try {
            // 1. Fazer POST para atualizar a entidade
            URL postUrl = new URL("https://apichallenges.eviltester.com/sim/entities/" + entityId);
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();

            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setDoOutput(true);

            DataOutputStream outputStream = new DataOutputStream(postConnection.getOutputStream());
            outputStream.writeBytes(updateJson);
            outputStream.flush();
            outputStream.close();

            int postStatus = postConnection.getResponseCode();
            System.out.println("POST - Código de status HTTP: " + postStatus);

            BufferedReader postReader = new BufferedReader(
                    new InputStreamReader(postConnection.getInputStream())
            );
            String line;
            StringBuilder postResponse = new StringBuilder();
            while ((line = postReader.readLine()) != null) {
                postResponse.append(line);
            }
            postReader.close();
            System.out.println("POST - Corpo da resposta:");
            System.out.println(postResponse.toString());

            postConnection.disconnect();

            // 2. Fazer GET para verificar se foi atualizado
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
            System.out.println("Erro ao atualizar ou recuperar a entidade: " + e.getMessage());
        }
    }
}
