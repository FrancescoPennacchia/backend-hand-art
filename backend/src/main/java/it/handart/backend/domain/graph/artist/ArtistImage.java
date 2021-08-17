package it.handart.backend.domain.graph.artist;

import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GraphQLProperty(name="image")
public class ArtistImage {

    private String url;

}
