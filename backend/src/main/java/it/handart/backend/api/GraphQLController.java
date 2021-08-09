package it.handart.backend.api;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/api/graph")
public class GraphQLController {

    @Value("${artsy.X-XAPP-Token}")
    private String Token;

    @Value("${artsy.graphql.url}")
    private String url_graphql;

    /* Richiesta per singolo Artwork e Artist*/
    @RequestMapping("/artwork/{idArtwork}")
    public void getArtworkAndArtistById(@PathVariable String idArtwork) throws MalformedURLException {
        GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                .url(url_graphql)
                .scalars(Date.class)
                .arguments(new Arguments("getThreads", new Argument<>("input", "argument")))
                .request(ThreadResult.class)
                .build();

    }
}
