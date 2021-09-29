package it.handart.backend.api;

import it.handart.backend.business.HandArtArtworkService;
import it.handart.backend.domain.rest.ArtistaPreferito;
import it.handart.backend.domain.rest.OperaPreferita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/rest")
public class ArtworksController {

    @Autowired
    private HandArtArtworkService service;

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

    /* Richiesta lista artworks per genere */
    @RequestMapping("/artworks/genes")
    public String getArtworksByGenes(@RequestParam int size, @RequestParam String genes) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url_rest + "/artworks/?genes=" + genes + "&size=" + size ))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

    /* Richiesta lista generi */
    @RequestMapping("/genes")
    public String getGenes(@RequestParam int size) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url_rest + "/genes?size=" + size ))
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

    /* Aggiungi opera favorito */
    @PostMapping("/add/artwork/favorite")
    public void addArtwork(@RequestBody OperaPreferita opera) {
        service.addFavoriteArtwork(opera);
    }

    /* Cancella opera favorito */
    @DeleteMapping("/delete/artwork/favorite")
    public void deleteArtwork(@RequestParam long  idOperaPreferita) {
        service.deleteFavoriteArtwork(idOperaPreferita);
    }

    /* Lista opere favorite */



    @RequestMapping("/get/artworks/favorites")
    public List<OperaPreferita> getFavoritesArtworks(@RequestParam long  id) {
        return service.getFavoritesArtworks(id);
    }

    @RequestMapping("/get/artwork/favorite")
    public OperaPreferita getFavoriteArtwork(@RequestParam long  idUtente, @RequestParam String idOpera) {
        return service.getFavoriteArtwork(idOpera, idUtente);
    }


}


        /* VECCHIO CODICE DI RICHIESTA */
        /*
            RestTemplate restTemplate = new RestTemplate(); / /1
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("X-XAPP-Token", Toke);

            HttpEntity<String> entity = new HttpEntity<>("body", headers);


            String url = "https://api.artsy.net/api/artworks?size=1"; //2
            String response
                    = restTemplate.postForObject(url, entity,  String.class); //3

            System.out.println(response);
            return  response;*/