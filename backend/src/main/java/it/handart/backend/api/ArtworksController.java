package it.handart.backend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.handart.backend.domain.response.ArtworkResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.Collections;

@RestController
@RequestMapping("/api")
public class ArtworksController {

    @Value("${artsy.X-XAPP-Token}")
    private String Token;

    @RequestMapping("/artwork")
    public String getArtwork() throws JsonProcessingException {

            RestTemplate restTemplate = new RestTemplate(); //1
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("X-XAPP-Token", Token);

            HttpEntity<String> entity = new HttpEntity<>("body", headers);


            String url = "https://api.artsy.net/api/artworks?size=1"; //2
            String response
                    = restTemplate.postForObject(url, entity,  String.class); //3

            System.out.println(response);
        return  response;
    }


}
