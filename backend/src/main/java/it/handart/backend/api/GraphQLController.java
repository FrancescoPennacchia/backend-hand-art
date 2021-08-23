package it.handart.backend.api;

import io.aexp.nodes.graphql.*;
import io.aexp.nodes.graphql.exceptions.GraphQLException;
import io.aexp.nodes.graphql.internal.Error;
import it.handart.backend.domain.graph.artist.Artist;
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
    public Artwork getArtworkAndArtistById(@RequestParam String idArtwork) throws MalformedURLException {

        Map<String, String> headers = new HashMap<>();
        headers.put("X-XAPP-Token", Token);

        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();

        GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                .url(url_graphql)
                .headers(headers)
                .arguments(new Arguments("artwork", new Argument<>("id", idArtwork)))
                .request(Artwork.class)
                .build();

        GraphQLResponseEntity<Artwork> responseEntity = graphQLTemplate.query(requestEntity, Artwork.class);

        if (responseEntity.getErrors() != null && responseEntity.getErrors().length > 0) {
            Error error = responseEntity.getErrors()[0];
            throw new GraphQLException(error.getMessage());
        }
        return responseEntity.getResponse();
    }

    /* Richiesta Artisti popolari */
    @RequestMapping("/popular_artists")
    public List<Artist> getPopularArtist(@RequestParam int size) throws MalformedURLException {

        Map<String, String> headers = new HashMap<>();
        headers.put("X-XAPP-Token", Token);

        List<Artist> result = new ArrayList<>();

        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        try {
            GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                    .url(url_graphql)
                    .headers(headers)
                    .arguments(new Arguments("popular_artists", new Argument<>("size", size)))
                    .request(Artist.class)
                    .build();

            GraphQLResponseEntity<Artist> responseEntity = graphQLTemplate.query(requestEntity, Artist.class);
            result = responseEntity.getResponse();
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
