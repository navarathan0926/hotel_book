package com.example.demo.Utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class HotelApiConfig {

    public Map<String,String> hotelApi_headers = new HashMap<>();

    public HotelApiConfig(String session_id){
       hotelApi_headers.put("Content-Type","application/xml");
       hotelApi_headers.put("Accept","application/xml");
       hotelApi_headers.put("Accept-Encoding", "gzip");
       hotelApi_headers.put("api-version", "3.92");
       hotelApi_headers.put("accessmode", "agency");
       hotelApi_headers.put("accessid", "eflycha eflycha");
       hotelApi_headers.put("Connection", "close");
    }

}
