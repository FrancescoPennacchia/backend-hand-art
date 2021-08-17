package it.handart.backend.domain.graph.artwork;

import io.aexp.nodes.graphql.annotations.GraphQLProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GraphQLProperty(name="images")
public class ArtworkImages {

    private String id;
    private String image_url;
    private String is_default;
    private String original_height;
    private String original_width;
    private String aspect_ratio;
    private String max_tiled_height;
    private String max_tiled_width;
    private String tile_size;
    private String tile_base_url;
    private String tile_format;

}
