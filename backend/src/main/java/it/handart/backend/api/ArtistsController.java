package it.handart.backend.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ArtistsController {

    @Value("${artsy.X-XAPP-Token}")
    private String Token;

    @Value("${artsy.url}")
    private String url;

}
