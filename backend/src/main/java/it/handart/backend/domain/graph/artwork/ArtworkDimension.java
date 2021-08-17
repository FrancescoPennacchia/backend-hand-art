package it.handart.backend.domain.graph.artwork;

import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GraphQLProperty(name="dimensions")
public class ArtworkDimension {

    private String cm;
    private String in;

}
