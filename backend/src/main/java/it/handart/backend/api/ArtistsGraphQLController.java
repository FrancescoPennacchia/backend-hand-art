package it.handart.backend.api;

import io.aexp.nodes.graphql.*;
import io.aexp.nodes.graphql.exceptions.GraphQLException;
import io.aexp.nodes.graphql.internal.Error;
import it.handart.backend.domain.graph.artist.Artist;
import it.handart.backend.domain.graph.artist.ArtistResponseList;
import it.handart.backend.domain.graph.artist.PopularArtists;
import it.handart.backend.domain.graph.artwork.Artwork;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/graph")
public class ArtistsGraphQLController {

    @Value("${artsy.X-XAPP-Token}")
    private String Token;

    @Value("${artsy.graphql.url}")
    private String url_graphql;

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
