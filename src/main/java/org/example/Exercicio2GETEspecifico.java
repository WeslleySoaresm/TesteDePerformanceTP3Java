package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio2GETEspecifico {

    public static void main(String[] args) {
        // Laço para buscar entidades com ID de 1 a 8
        for (int id = 1; id <= 8; id++) {
            try {
                System.out.println("=== Entidade ID: " + id + " ===");

                URL url = new URL("https://apichallenges.eviltester.com/sim/entities/" + id);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int statusCode = connection.getResponseCode();
                System.out.println("Código de status HTTP: " + statusCode);

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );

                String line;
                StringBuilder responseBody = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }

                reader.close();
                connection.disconnect();

                System.out.println("Corpo da resposta:");
                System.out.println(responseBody.toString());
                System.out.println(); // Linha em branco entre entidades

            } catch (Exception e) {
                System.out.println("Erro ao buscar entidade ID " + id + ": " + e.getMessage());
            }
        }
    }
}
