package it.handart.backend.api;


import io.aexp.nodes.graphql.*;
import io.aexp.nodes.graphql.exceptions.GraphQLException;
import io.aexp.nodes.graphql.internal.Error;
import it.handart.backend.domain.graph.artwork.Artwork;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/graph")
public class ArtworksGraphQLController {

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
}
