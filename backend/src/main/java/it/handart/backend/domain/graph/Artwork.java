package it.handart.backend.domain.graph;


import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@GraphQLProperty(name="getArtwork", arguments = @GraphQLArgument(name = "input"))
public class Artwork {

    private String id;
    private String _id;
    private String title;
    private String provenance;

    public Artwork() {

    }

    public Artwork(String id, String _id, String title, String provenance) {
        this.id = id;
        this._id = _id;
        this.title = title;
        this.provenance = provenance;
    }
}
