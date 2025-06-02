package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio10DELETEInvalido {

    public static void main(String[] args) {
        int entityId = 2;

        try {
            URL deleteUrl = new URL("https://apichallenges.eviltester.com/sim/entities/" + entityId);
            HttpURLConnection connection = (HttpURLConnection) deleteUrl.openConnection();
            connection.setRequestMethod("DELETE");

            int statusCode = connection.getResponseCode();
            System.out.println("DELETE inválido - Código de status HTTP: " + statusCode);

            BufferedReader reader;

            // Para códigos de erro, usar getErrorStream()
            if (statusCode >= 400) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }

            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
            reader.close();

            System.out.println("DELETE inválido - Corpo da resposta:");
            System.out.println(responseBody.toString());

            connection.disconnect();

        } catch (Exception e) {
            System.out.println("Erro ao tentar DELETE inválido: " + e.getMessage());
        }
    }
}
