package it.handart.backend.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/graph")
public class GraphQLController {

    @Value("${artsy.X-XAPP-Token}")
    private String Token;

    @Value("${artsy.graphql.url}")
    private String url_graphql;

    /* Richiesta per singolo Artwork e Artist*/
    @RequestMapping("/artwork/{idArtwork}")
    public void getArtworkAndArtistById(@PathVariable String idArtwork) {

    }
}
