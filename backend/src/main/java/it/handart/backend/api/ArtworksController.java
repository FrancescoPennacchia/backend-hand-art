package it.handart.backend.api;

import it.handart.backend.domain.response.ArtworkResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@RestController
@RequestMapping("/api")
public class ArtworksController {

    @Value("${artsy.X-XAPP-Token}")
    private String Token;

    @RequestMapping("/artwork")
    public ResponseEntity<String> getArtwork() {

            RestTemplate restTemplate = new RestTemplate(); //1
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-XAPP-Token", Token);
            HttpEntity<String> entity = new HttpEntity<>("body", headers);

            System.out.println(entity);

            String url = "https://api.artsy.net/api/artworks?size=1"; //2
            ResponseEntity<String> response
                    = restTemplate.postForEntity(url, entity,  String.class); //3

System.out.println(response);
        return  response;
    }


}
