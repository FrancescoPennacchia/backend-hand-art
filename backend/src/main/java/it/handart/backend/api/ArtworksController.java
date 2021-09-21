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
@RequestMapping("/api/rest")
public class ArtworksController {

    @Value("${artsy.X-XAPP-Token}")
    private String Token;

    @Value("${artsy.rest.url}")
    private String url_rest;

    /* Richiesta lista artworks per size */
    @RequestMapping("/artworks/size")
    public String getArtworksBySize(@RequestParam int size) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url_rest + "/artworks/?size=" + size ))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

    /* Richiesta lista artworks */
    @RequestMapping("/artworks")
    public String getArtworks() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url_rest + "/artworks/?size=50"))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

    /* Richiesta per singolo Artwork */
    @RequestMapping("/artwork")
    public String getArtworkById(@RequestParam String idArtwork) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url_rest + "/artworks/" + idArtwork))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

    /* Richiesta per singolo Artwork random */
    @RequestMapping("/artwork/sample")
    public String getRandomArtwork() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url_rest + "/artworks/?sample="))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

    /* Richiesta lista artworks in base all'ordine */
    @RequestMapping("/artworks/sort")
    public String getArtworksBySort(@RequestParam String sort) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url_rest + "/artworks/?sort=" + sort ))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

    /* Richiesta lista artworks per offset */
    @RequestMapping("/artworks/offset")
    public String getArtworksByOffset(@RequestParam int offset) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url_rest + "/artworks/?offset=" + offset ))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

}


        /* VECCHIO CODICE DI RICHIESTA */
        /*
            RestTemplate restTemplate = new RestTemplate(); //1
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("X-XAPP-Token", Toke);

            HttpEntity<String> entity = new HttpEntity<>("body", headers);


            String url = "https://api.artsy.net/api/artworks?size=1"; //2
            String response
                    = restTemplate.postForObject(url, entity,  String.class); //3

            System.out.println(response);
            return  response;*/