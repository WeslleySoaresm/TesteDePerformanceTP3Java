package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio3GETInexistente {

    public static void main(String[] args) {
        try {
            int id = 13;
            System.out.println("Buscando entidade com ID " + id + "...");

            URL url = new URL("https://apichallenges.eviltester.com/sim/entities/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int statusCode = connection.getResponseCode();
            System.out.println("Código de status HTTP: " + statusCode);

            if (statusCode == 404) {
                System.out.println("Entidade não encontrada. ID inválido ou inexistente.");
            } else {
                // Se não for erro, imprime a resposta
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );

                String line;
                StringBuilder responseBody = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }

                reader.close();

                System.out.println("Corpo da resposta:");
                System.out.println(responseBody.toString());
            }

            connection.disconnect();

        } catch (Exception e) {
            System.out.println("Erro durante a requisição: " + e.getMessage());
        }
    }
}
