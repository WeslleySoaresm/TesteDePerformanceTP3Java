package org.example;

import java.net.HttpURLConnection;
import java.net.URL;

public class Exercicio11OPTIONS {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://apichallenges.eviltester.com/sim/entities");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("OPTIONS");

            // Fazer a requisição
            int statusCode = connection.getResponseCode();
            System.out.println("Código de status HTTP: " + statusCode);

            // Ler o cabeçalho Allow
            String allowMethods = connection.getHeaderField("Allow");
            System.out.println("Métodos HTTP permitidos (Allow): " + allowMethods);

            connection.disconnect();

        } catch (Exception e) {
            System.out.println("Erro na requisição OPTIONS: " + e.getMessage());
        }
    }
}
