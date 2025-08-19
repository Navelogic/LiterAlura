package com.github.navelogic.literalura.Service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ApiConsumer {
    public String fetchData(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response;
        try {
            System.out.println("Buscando dados da URL: " + url);
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Resposta do servidor: " + response.statusCode());
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Falha ao buscar dados da API: " + e.getMessage(), e);
        }
        return response.body();
    }
}
