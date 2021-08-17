package it.handart.backend.domain.graph.artist;

import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GraphQLProperty(name="artist")
public class Artist {

    private String id;
    private String _id;
    private String name;
    private String years;
    private String birthday;
    private String nationality;
    private String blurb;
    private ArtistImage image;

}
