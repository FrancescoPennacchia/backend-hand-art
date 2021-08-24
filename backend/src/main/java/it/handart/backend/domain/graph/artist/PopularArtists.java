package it.handart.backend.domain.graph.artist;


import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import it.handart.backend.domain.graph.artwork.Artwork;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@GraphQLProperty(name="popular_artists", arguments = @GraphQLArgument(name = "size"))
public class PopularArtists {

    private List<Artist> artists;

}
