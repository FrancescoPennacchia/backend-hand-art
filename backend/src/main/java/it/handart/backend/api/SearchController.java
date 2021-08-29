package it.handart.backend.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/rest")
public class SearchController {

    @Value("${artsy.X-XAPP-Token}")
    private String Token;

    @Value("${artsy.rest.url}")
    private String url_rest;

    /* Ricerca */
    @RequestMapping("/search/{value}")
    public String searchArtistArtwork(@PathVariable String value) throws IOException, InterruptedException {

        String withOutSpace;
        withOutSpace = value.replace(" ", "+"); //Rimozione degli spazi

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url_rest + "/search?q=" + withOutSpace))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }
}
