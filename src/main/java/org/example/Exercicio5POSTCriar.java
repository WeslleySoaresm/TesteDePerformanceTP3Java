package org.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio5POSTCriar {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar o método e cabeçalhos
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true); // Habilita envio de dados no corpo da requisição

            // Corpo da requisição
            String jsonInput = "{\"name\": \"aluno\"}";

            // Enviar JSON
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(jsonInput);
            outputStream.flush();
            outputStream.close();

            // Código de status
            int statusCode = connection.getResponseCode();
            System.out.println("Código de status HTTP: " + statusCode);

            // Ler a resposta
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

            // Imprimir corpo da resposta
            System.out.println("Corpo da resposta:");
            String response = responseBody.toString();
            System.out.println(response);

            // Opcional: identificar o ID gerado
            if (response.contains("\"id\":")) {
                int idStart = response.indexOf("\"id\":") + 5;
                int idEnd = response.indexOf(",", idStart);
                if (idEnd == -1) idEnd = response.length() - 1;
                String id = response.substring(idStart, idEnd).replaceAll("[^0-9]", "");
                System.out.println("ID gerado: " + id);
            }

        } catch (Exception e) {
            System.out.println("Erro ao enviar a requisição POST: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
