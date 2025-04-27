package com.example.demo.Controller;


import com.example.demo.APIRequestProperties.XMLRequestBodies;
import com.example.demo.ApiBodies.RateBasis;
import com.example.demo.Services.RateBasisService;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static com.example.demo.Utils.EndPointURI.getRateBasisIds;

@RestController
@RequestMapping("/api")
public class RateBasisController {

    @Autowired
    private XMLRequestBodies xmlRequestBodies;

    @Autowired
    private RateBasisService rateBasisService;


    private final RestTemplate restTemplate = new RestTemplate();
    private final String EXTERNAL_API_URL = "https://xmldev.dotwconnect.com/gatewayV4.dotw";


    @PostMapping("/get-rate-basis-ids")
    public ResponseEntity<?> getRateBasisIds() {
        try {
            String xmlRequest = xmlRequestBodies.xmlBody_RateBasis();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_XML);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

            HttpEntity<String> entity = new HttpEntity<>(xmlRequest, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(EXTERNAL_API_URL, entity, String.class);

            String xmlResponse = response.getBody();
            JSONObject jsonObject = XML.toJSONObject(xmlResponse);

            return ResponseEntity.ok(jsonObject.toMap());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/getRateBasisIds")
    public ResponseEntity<?> getRateBasisId() {
        try {
            var result = rateBasisService.getRateBasisIds();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getMessage());
        }
    }


    @PostMapping(getRateBasisIds)
    public ResponseEntity<?> getRateBasis() {
        try {
            List<RateBasis> result = rateBasisService.getRateBasisList();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getMessage());
        }
    }

}

