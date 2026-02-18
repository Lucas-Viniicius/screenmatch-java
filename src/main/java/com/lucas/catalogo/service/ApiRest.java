package com.lucas.catalogo.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiRest {
    
    public String pesquisar(String url){
        URI endereco = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder()
                            .uri(endereco)
                            .GET()
                            .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> resposta = null;

        try{
            resposta = client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch(Exception e){
            throw new RuntimeException("ERRO AO CONSUMIR API OMDB", e);
        }

        String json = resposta.body();
        return json;
    }
}
