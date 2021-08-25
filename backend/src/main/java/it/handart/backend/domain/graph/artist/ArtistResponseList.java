package it.handart.backend.domain.graph.artist;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@GraphQLProperty(name="artists", arguments = @GraphQLArgument(name = "size"))
public class ArtistResponseList {

    private List<Artist> artists;

}
