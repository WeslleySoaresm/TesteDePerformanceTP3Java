package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio1GET {

    public static void main(String[] args) {
        try {
            // 1. Criar a URL do endpoint
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities");

            // 2. Abrir conexão
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 3. Definir o método HTTP
            connection.setRequestMethod("GET");

            // 4. Obter o código de resposta
            int statusCode = connection.getResponseCode();
            System.out.println("Código de status HTTP: " + statusCode);

            // 5. Ler a resposta
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            String line;
            StringBuilder responseBody = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }

            reader.close();

            // 6. Imprimir o corpo da resposta
            System.out.println("Corpo da resposta:");
            System.out.println(responseBody.toString());

            // 7. Fechar a conexão
            connection.disconnect();

        } catch (Exception e) {
            System.out.println("Erro na requisição: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
