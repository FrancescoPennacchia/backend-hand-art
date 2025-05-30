package it.handart.backend.business.impl;

import io.aexp.nodes.graphql.*;
import io.aexp.nodes.graphql.exceptions.GraphQLException;
import io.aexp.nodes.graphql.internal.Error;
import it.handart.backend.domain.graph.artwork.Artwork;
import org.springframework.beans.factory.annotation.Value;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class HandArtGraphService {

    @Value("${artsy.X-XAPP-Token}")
    private String Token;

    @Value("${artsy.graphql.url}")
    private String url_graphql;

    public Artwork getArtworkAndArtistById(String idArtwork) throws MalformedURLException {
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
}
