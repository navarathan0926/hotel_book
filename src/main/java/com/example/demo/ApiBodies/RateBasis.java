package com.example.demo.ApiBodies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class RateBasis {
    @JacksonXmlProperty(localName = "runno")
    private Long runno;

    @JacksonXmlProperty(localName = "value")
    private Long roomCode;

    @JacksonXmlProperty(localName = "content")
    private String roomType;
}

