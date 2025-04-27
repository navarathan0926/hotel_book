package com.example.demo.ApiBodies;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Country {
    private String runno;
    private String countryCode;
    private String countryName;
}
