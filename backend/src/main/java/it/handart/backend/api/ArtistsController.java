package it.handart.backend.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api")
public class ArtistsController {

    @Value("${artsy.X-XAPP-Token}")
    private String Token;

    @Value("${artsy.url}")
    private String url;

    /* Richiesta per singolo artist by ID */
    @RequestMapping("/artist/{idArtist}")
    public String getArtistById(@PathVariable String idArtist) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url + "/artists/" + idArtist))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

    /* Richiesta lista artists per size */
    @RequestMapping("/artists/size")
    public String getArtistsBySize(@RequestParam int size) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url + "/artists/?size=" + size ))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

    /* Richiesta per singolo Artist random */
    @RequestMapping("/artists/sample")
    public String getRandomArtist() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url + "/artists/?sample="))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

    /* Richiesta lista artists in base all'ordine */
    @RequestMapping("/artists/sort")
    public String getArtistsBySort(@RequestParam String sort) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url + "/artworks/?sort=" + sort ))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

    /* Richiesta lista artists per offset */
    @RequestMapping("/artists/offset")
    public String getArtistsByOffset(@RequestParam int offset) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url + "/artists/?offset=" + offset ))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

}
