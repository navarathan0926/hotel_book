package com.example.demo.Services;


import com.example.demo.APIRequestProperties.XMLHeaders;
import com.example.demo.APIRequestProperties.XMLRequestBodies;
import com.example.demo.ApiBodies.City;
import com.example.demo.ApiBodies.RateBasis;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class RateBasisService {

    @Autowired
    private XMLRequestBodies xmlRequestBodies;

    @Autowired
    private XMLHeaders xmlHeaders;

    private RateBasis rateBasis;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String EXTERNAL_API_URL = "https://xmldev.dotwconnect.com/gatewayV4.dotw";



    public Map<String, Object> getRateBasisIds() throws Exception {
        String xmlRequest = xmlRequestBodies.xmlBody_RateBasis();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

        HttpEntity<String> entity = new HttpEntity<>(xmlRequest, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(EXTERNAL_API_URL, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String xmlResponse = response.getBody();
            JSONObject jsonObject = XML.toJSONObject(xmlResponse);

            return jsonObject.toMap();
        } else {
            throw new RuntimeException("Failed to fetch data from external API");
        }
    }

    public List<RateBasis> getRateBasisList() {
        try {
            String xmlRequest = xmlRequestBodies.xmlBody_RateBasis();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_XML);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

            HttpEntity<String> entity = new HttpEntity<>(xmlRequest, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(EXTERNAL_API_URL, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                String xmlResponse = response.getBody();
                JSONObject jsonObject = XML.toJSONObject(xmlResponse);
                JSONArray rateArray = jsonObject.getJSONObject("result").getJSONObject("ratebasis").getJSONArray("option");

                List<RateBasis> rateList = new ArrayList<>();
                rateArray.forEach(obj -> {
                    JSONObject rateJson = (JSONObject) obj;
                    RateBasis rateBasis = RateBasis.builder()
                            .runno(rateJson.getLong("runno"))
                            .roomCode(rateJson.getLong("value"))
                            .roomType(rateJson.getString("content"))
                            .build();
                    rateList.add(rateBasis);
                });
                return rateList;
            } else {
                throw new RuntimeException("Failed to fetch data from external API");
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("HTTP error occurred: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred: " + e.getMessage(), e);
        }
    }


}
