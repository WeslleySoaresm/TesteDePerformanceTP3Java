package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio9DELETE {

    public static void main(String[] args) {
        int entityId = 9;

        try {
            // 1. DELETE da entidade
            URL deleteUrl = new URL("https://apichallenges.eviltester.com/sim/entities/" + entityId);
            HttpURLConnection deleteConnection = (HttpURLConnection) deleteUrl.openConnection();
            deleteConnection.setRequestMethod("DELETE");

            int deleteStatus = deleteConnection.getResponseCode();
            System.out.println("DELETE - Código de status HTTP: " + deleteStatus);

            deleteConnection.disconnect();

            // 2. GET para verificar se a entidade foi removida
            URL getUrl = new URL("https://apichallenges.eviltester.com/sim/entities/" + entityId);
            HttpURLConnection getConnection = (HttpURLConnection) getUrl.openConnection();
            getConnection.setRequestMethod("GET");

            int getStatus = getConnection.getResponseCode();
            System.out.println("GET após DELETE - Código de status HTTP: " + getStatus);

            if (getStatus == 404) {
                System.out.println("Confirmação: entidade não encontrada (404) após exclusão.");
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(getConnection.getInputStream()));
                StringBuilder responseBody = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }
                reader.close();

                System.out.println("GET após DELETE - Corpo da resposta:");
                System.out.println(responseBody.toString());
            }

            getConnection.disconnect();

        } catch (Exception e) {
            System.out.println("Erro ao deletar ou verificar entidade: " + e.getMessage());
        }
    }
}

