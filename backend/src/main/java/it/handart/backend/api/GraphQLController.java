package it.handart.backend.api;

import io.aexp.nodes.graphql.*;
import io.aexp.nodes.graphql.exceptions.GraphQLException;
import io.aexp.nodes.graphql.internal.Error;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
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

    /* Richiesta per singolo Artwork e Artist*/
    @RequestMapping("/artwork")
    public void getArtworkAndArtistById(@RequestParam String idArtwork) throws MalformedURLException {

        String result = "";

        Map<String, String> headers = new HashMap<>();
        headers.put("X-XAPP-Token", Token);

        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();

        GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                .url(url_graphql)
                .scalars(Date.class)
                .headers(headers)
                .request("query {  artwork(id: \"" +  idArtwork + "\") {\n" +
                        "    # Useful IDs\n" +
                        "    id\n" +
                        "    _id\n" +
                        "    title\n" +
                        "    provenance\n" +
                        "    \n" +
                        "\n" +
                        "   # Eigen only supports 1 artist on an Artwork, ideally this would be 'artists'\n" +
                        "    artist {\n" +
                        "      #  Enough information on an Artist to show the metadata\n" +
                        "      id\n" +
                        "      _id\n" +
                        "      name\n" +
                        "      years\n" +
                        "      birthday\n" +
                        "      nationality\n" +
                        "      blurb\n" +
                        "      # A preview image for the artist if we'll need it\n" +
                        "      image {\n" +
                        "        url\n" +
                        "      }\n" +
                        "      sortable_id\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    # We need information about the Artwork's image to do view in room, AR, or\n" +
                        "    # the tiled zoom\n" +
                        "    images {\n" +
                        "      id\n" +
                        "      image_versions\n" +
                        "      image_url\n" +
                        "      is_default\n" +
                        "      original_height\n" +
                        "      original_width\n" +
                        "      aspect_ratio\n" +
                        "      max_tiled_height\n" +
                        "      max_tiled_width\n" +
                        "      tile_size\n" +
                        "      tile_base_url\n" +
                        "      tile_format\n" +
                        "    }\n" +
                        "\n" +
                        "    # Useful for view in room / AR\n" +
                        "    dimensions {\n" +
                        "      cm\n" +
                        "      in\n" +
                        "    }\n" +
                        "   }\n" +
                        " }")
                .build();

        System.out.println(requestEntity.toString());

        GraphQLResponseEntity<String> responseEntity = graphQLTemplate.query(requestEntity, String.class);

        System.out.println("SONO QUI");

        if (responseEntity.getErrors() != null && responseEntity.getErrors().length > 0) {
            Error error = responseEntity.getErrors()[0];
            throw new GraphQLException(error.getMessage());
        }
        //result = responseEntity.getResponse();

        System.out.println(result);
    }
}
