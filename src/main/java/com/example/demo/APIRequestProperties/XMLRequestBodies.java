package com.example.demo.APIRequestProperties;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class XMLRequestBodies {

    public String hotelUrl = "https://xmldev.dotwconnect.com/gatewayV4.dotw";

           String userName = "EflyAGZurichXML";
           String password = "eaa90584f5a8796697f2b3da6fdabc34";
           String id = "2167915";
           String source = "1";

           //request commands
           String requestCommand_Cities = "getallcities";
//           String requestCommand_Countries = "getallcountries";
//           String getRequestCommand_ServingCities = "getservingcities";
           String searchHotels = "searchhotels";
           String getRequestCommand_RateBasisById  = "getratebasisids";

    public String xmlBody_city(){

        return String.format(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<customer>" +
                        "<username>%s</username>" +
                        "<password>%s</password>" +
                        "<id>%s</id>" +
                        "<source>%s</source>" +
                        "<request command=\"%s\"/>" +
                        "</customer>",
                userName, password, id, source, requestCommand_Cities
        );
    }

    public String xmlBody_RateBasis(){
        return String.format(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
                        "<customer>" +
                        "<username>%s</username>" +
                        "<password>%s</password>" +
                        "<id>%s</id>" +
                        "<source>%s</source>" +
                        "<request command=\"%s\"/>" +
                        "</customer>",
                userName, password, id, source, getRequestCommand_RateBasisById
        );
    }

}
