package it.handart.backend.api;

import it.handart.backend.business.HandArtArtistService;
import it.handart.backend.domain.rest.ArtistaPreferito;
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
public class ArtistsRESTController {

    @Autowired
    private HandArtArtistService service;

    @Value("${artsy.X-XAPP-Token}")
    private String Token;

    @Value("${artsy.rest.url}")
    private String url_rest;

    /* Richiesta per singolo artist by ID */
    @RequestMapping("/artist/{idArtist}")
    public String getArtistById(@PathVariable String idArtist) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url_rest + "/artists/" + idArtist))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

    /* Richiesta lista artists per size */
    @RequestMapping("/artists/size")
    public String getArtistsBySize(@RequestParam String size) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();                         // Nuova richiesta HTTP
        HttpRequest request = HttpRequest.newBuilder()                          // Costruiamo la richiesta HTTP
                .uri(URI.create( url_rest + "/artists/?size=" + size ))         // URI della richiesta
                .headers("X-XAPP-Token", Token)                                 // HEADERS
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());  //Effettua la richiesta

        return response.body().toString();
    }

    /* Richiesta per singolo Artist random */
    @RequestMapping("/artists/sample")
    public String getRandomArtist() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url_rest + "/artists/?sample="))
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
                .uri(URI.create( url_rest + "/artworks/?sort=" + sort ))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

    /* Richiesta lista artists per offset */
    @RequestMapping("/artists/offset")
    public String getArtistsByOffset(@RequestParam String offset) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create( url_rest + "/artists/?offset=" + offset ))
                .headers("X-XAPP-Token", Token)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

    /* Aggiungi artista favorito */
    @PostMapping("/add/artist/favorite")
    public void addArtist(@RequestBody ArtistaPreferito artista) {
        service.addFavoriteArtist(artista);
    }

    /* Cancella artista favorito */
    @DeleteMapping("/delete/artist/favorite")
    public void deleteArtist(@RequestParam long idUtente) {
        service.deleteFavoriteArtist(idUtente);
    }

    @RequestMapping("/get/artists/favorites")
    public List<ArtistaPreferito> getFavoritesArtists(@RequestParam long idUtente){
        return service.getFavoritesArtists( idUtente );
    }

    @RequestMapping("/get/artist/favorite")
    public ArtistaPreferito getFavoriteArtist(@RequestParam long idUtente, @RequestParam String idAutore) {
         // System.out.println(idUtente + idAutore);
        return service.getFavoriteArtist( idUtente, idAutore );
    }

}
