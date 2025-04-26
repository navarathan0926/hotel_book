package com.example.demo.ApiBodies;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RateBase {
//    private Long rateTypeCode;
//    private Long rateBaseId;
//    private Long currencyCode;
//    private Double totalAmount;
    private  Long roomCode;
    private  Long runno;
    private String roomName;
}
