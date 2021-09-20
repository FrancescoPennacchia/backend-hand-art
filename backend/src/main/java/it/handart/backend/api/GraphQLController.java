package it.handart.backend.api;

import io.aexp.nodes.graphql.*;
import io.aexp.nodes.graphql.exceptions.GraphQLException;
import io.aexp.nodes.graphql.internal.Error;
import it.handart.backend.domain.graph.artist.Artist;
import it.handart.backend.domain.graph.artist.ArtistResponseList;
import it.handart.backend.domain.graph.artist.PopularArtists;
import it.handart.backend.domain.graph.artwork.Artwork;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.*;


@RestController
@RequestMapping("/api/graph")
public class GraphQLController {

    @Value("${artsy.X-XAPP-Token}")
    private String Token;

    @Value("${artsy.graphql.url}")
    private String url_graphql;

    /* Richiesta per singolo Artwork e Artist GraphQL*/
    @RequestMapping("/artwork")
    public Artwork getArtworkById(@RequestParam String idArtwork) throws MalformedURLException {

        Map<String, String> headers = new HashMap<>();
        headers.put("X-XAPP-Token", Token);

        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();

        GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()                            // Nuova richiesta GraphQL
                .url(url_graphql)                                                                     // Setaggio url end point
                .headers(headers)                                                                    // Header con token di autenticazione
                .arguments(new Arguments("artwork", new Argument<>("id", idArtwork)))       // Argomenti della query e paramentri
                .request(Artwork.class)                                                           // Ogetto richiesto Artwork
                .build();

        GraphQLResponseEntity<Artwork> responseEntity = graphQLTemplate.query(requestEntity, Artwork.class);    // Esecuzione query

        if (responseEntity.getErrors() != null && responseEntity.getErrors().length > 0) {
            Error error = responseEntity.getErrors()[0];
            throw new GraphQLException(error.getMessage());
        }
        return responseEntity.getResponse();
    }

    /* Richiesta Artisti popolari */
    @RequestMapping("/popular/artists")
    public List<Artist> getPopularArtist(@RequestParam int size) {

        Map<String, String> headers = new HashMap<>();
        headers.put("X-XAPP-Token", Token);

        List<Artist> result = new ArrayList<>();

        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        try {
            GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                    .url(url_graphql)
                    .headers(headers)
                    .arguments(new Arguments("popular_artists", new Argument<>("size", size)))
                    .request(PopularArtists.class)
                    .build();

            System.out.println(requestEntity.getRequest());
            GraphQLResponseEntity<PopularArtists> responseEntity = graphQLTemplate.query(requestEntity, PopularArtists.class);
            result = responseEntity.getResponse().getArtists();
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /* Richiesta per singolo Artwork e Artist GraphQL*/
    @RequestMapping("/artist")
    public Artist getArtistById(@RequestParam String idArtist) throws MalformedURLException {

        Map<String, String> headers = new HashMap<>();
        headers.put("X-XAPP-Token", Token);

        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();

        GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                .url(url_graphql)
                .headers(headers)
                .arguments(new Arguments("artist", new Argument<>("id", idArtist)))
                .request(Artist.class)
                .build();

        GraphQLResponseEntity<Artist> responseEntity = graphQLTemplate.query(requestEntity, Artist.class);

        if (responseEntity.getErrors() != null && responseEntity.getErrors().length > 0) {
            Error error = responseEntity.getErrors()[0];
            throw new GraphQLException(error.getMessage());
        }
        return responseEntity.getResponse();
    }

    /* Richiesta lista artisti */
    @RequestMapping("/artists")
    public List<Artist> getArtistsList(@RequestParam String size) throws MalformedURLException {

        Map<String, String> headers = new HashMap<>();
        headers.put("X-XAPP-Token", Token);

        List<Artist> result = new ArrayList<>();

        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        try {
            GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                    .url(url_graphql)
                    .headers(headers)
                    .arguments(new Arguments("artists", new Argument<>("size", size)))
                    .request(ArtistResponseList.class)
                    .build();

            System.out.println(requestEntity.getRequest());
            GraphQLResponseEntity<ArtistResponseList> responseEntity = graphQLTemplate.query(requestEntity, ArtistResponseList.class);
            result = responseEntity.getResponse().getArtists();
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
