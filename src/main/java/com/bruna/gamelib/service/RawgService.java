package com.bruna.gamelib.service;

import com.bruna.gamelib.dto.JogoRawgDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class RawgService {
    @Value("${rawg.api.key}")
    private String apiKey;

    @Value("${rawg.api.url}")
    private String apiUrl;

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<JogoRawgDTO> buscarJogosPorNome(String nome) throws Exception {
        String nomeCodificado = URLEncoder.encode(nome, StandardCharsets.UTF_8);
        String url = apiUrl + "/games?key=" + apiKey + "&search=" + nomeCodificado;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode json = objectMapper.readTree(response.body());
        JsonNode resultados = json.get("results");

        List<JogoRawgDTO> lista = new ArrayList<>();

        if (resultados != null && resultados.isArray()) {
            for (JsonNode jogo : resultados) {
                lista.add(converterParaDTO(jogo));
            }
        }

        return lista;
    }

    private String montarGeneros(JsonNode generosNode) {
        if (generosNode == null || !generosNode.isArray() || generosNode.isEmpty()) {
            return "";
        }

        StringBuilder generosTexto = new StringBuilder();

        for (int i = 0; i < generosNode.size(); i++) {
            JsonNode generoAtual = generosNode.get(i);
            String nomeGenero = generoAtual.get("name").asText();

            if (i > 0) {
                generosTexto.append(", ");
            }

            generosTexto.append(nomeGenero);
        }

        return generosTexto.toString();
    }

    private String montarPlataformas(JsonNode plataformasNode) {
        if (plataformasNode == null || !plataformasNode.isArray() || plataformasNode.isEmpty()) {
            return "";
        }

        StringBuilder plataformasTexto = new StringBuilder();

        for (int i = 0; i < plataformasNode.size(); i++) {
            JsonNode plataformaAtual = plataformasNode.get(i);
            String nomePlataforma = plataformaAtual.get("platform").get("name").asText();

            if (i > 0) {
                plataformasTexto.append(", ");
            }

            plataformasTexto.append(nomePlataforma);
        }

        return plataformasTexto.toString();
    }

    private JogoRawgDTO converterParaDTO(JsonNode jogo) {
        Integer id = jogo.get("id") != null ? jogo.get("id").asInt() : null;
        String nomeJogo = jogo.get("name") != null ? jogo.get("name").asText() : null;
        String dataLancamento = jogo.get("released") != null ? jogo.get("released").asText() : null;
        Double nota = jogo.get("rating") != null ? jogo.get("rating").asDouble() : null;

        String generos = montarGeneros(jogo.get("genres"));
        String plataformas = montarPlataformas(jogo.get("platforms"));

        return new JogoRawgDTO(id, nomeJogo, dataLancamento, nota, generos, plataformas);
    }

    public JogoRawgDTO buscarJogoPorId(Integer id) throws IOException, InterruptedException {
        String url = apiUrl + "/games/" + id + "?key=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            return null;
        }

        JsonNode jogo = objectMapper.readTree(response.body());

        return converterParaDTO(jogo);
    }

}
