package it.handart.backend.domain.graph.artwork;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import it.handart.backend.domain.graph.artist.Artist;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@GraphQLProperty(name="artwork", arguments = @GraphQLArgument(name = "id"))
public class Artwork {

    private String id;
    private String _id;
    private String title;
    private String category;
    private String provenance;
    private String signature;
    private String literature;
    private String medium;
    private String collecting_institution;
    private String date;
    private String additional_information;

    private Artist artist;
    private ArtworkDimension dimensions;
    private List<ArtworkImages> images;

}
