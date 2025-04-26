package com.example.demo.APIRequestProperties;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class XMLHeaders {
    public Map<String,String> xmlAPI_Headers = new HashMap<>();

    public XMLHeaders(){
        xmlAPI_Headers.put("Content-Type","application/xml");
        xmlAPI_Headers.put("Host","xmldev.dotwconnect.com");
        xmlAPI_Headers.put("Request-URL","/gatewayV4.dotw");
        xmlAPI_Headers.put("Request-Method","POST");
        xmlAPI_Headers.put("Compression","Gzip");
        xmlAPI_Headers.put("Connection","close");
    }
}
