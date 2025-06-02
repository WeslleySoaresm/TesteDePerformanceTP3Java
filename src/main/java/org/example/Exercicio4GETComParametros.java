package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Exercicio4GETComParametros {

    public static void main(String[] args) {
        try {
            // Parâmetros fictícios
            String categoria = "teste";
            String limite = "5";

            // Codificar os parâmetros
            String query = String.format("categoria=%s&limite=%s",
                    URLEncoder.encode(categoria, "UTF-8"),
                    URLEncoder.encode(limite, "UTF-8")
            );

            // Montar a URL com parâmetros
            String baseUrl = "https://apichallenges.eviltester.com/sim/entities";
            String fullUrl = baseUrl + "?" + query;

            // Mostrar a URL final
            System.out.println("URL final montada:");
            System.out.println(fullUrl);

            // Fazer a requisição GET
            URL url = new URL(fullUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Obter e mostrar o status
            int statusCode = connection.getResponseCode();
            System.out.println("Código de status HTTP: " + statusCode);

            // Ler a resposta (opcional aqui, mas vamos fazer)
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

        } catch (Exception e) {
            System.out.println("Erro ao montar ou enviar a requisição: " + e.getMessage());
        }
    }
}
