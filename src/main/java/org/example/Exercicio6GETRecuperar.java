package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio6GETRecuperar {

    public static void main(String[] args) {
        try {
            int id = 11; // ID da entidade criada no POST anterior
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Código de status
            int statusCode = connection.getResponseCode();
            System.out.println("Código de status HTTP: " + statusCode);

            // Ler resposta
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

            // Imprimir conteúdo retornado
            System.out.println("Corpo da resposta:");
            System.out.println(responseBody.toString());

        } catch (Exception e) {
            System.out.println("Erro ao buscar a entidade criada: " + e.getMessage());
        }
    }
}
