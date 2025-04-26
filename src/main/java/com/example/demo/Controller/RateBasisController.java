package com.example.demo.Controller;


import com.example.demo.APIRequestProperties.XMLRequestBodies;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class RateBasisController {

    @Autowired
    private XMLRequestBodies xmlRequestBodies;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String EXTERNAL_API_URL = "https://xmldev.dotwconnect.com/gatewayV4.dotw";



    @PostMapping("/get-rate-basis-ids")
    public ResponseEntity<?> getRateBasisIds() {
        try {
            String xmlRequest = xmlRequestBodies.xmlBody_RateBasis();

            // 2. Set HTTP headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_XML);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

            HttpEntity<String> entity = new HttpEntity<>(xmlRequest, headers);

            // 3. Send POST request
            ResponseEntity<String> response = restTemplate.postForEntity(EXTERNAL_API_URL, entity, String.class);

            // 4. Convert XML response to JSON
            String xmlResponse = response.getBody();
            JSONObject jsonObject = XML.toJSONObject(xmlResponse);

            // 5. Return JSON
            return ResponseEntity.ok(jsonObject.toMap()); // convert JSONObject to Map directly
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}

