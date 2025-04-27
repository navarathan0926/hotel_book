package com.example.demo.ApiBodies;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class City {
    private String runno;
    private String cityName;
    private String cityCode;
    private Country country;
}
