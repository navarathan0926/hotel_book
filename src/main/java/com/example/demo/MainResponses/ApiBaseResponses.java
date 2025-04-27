package com.example.demo.MainResponses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiBaseResponses {
    private String validation_status;
    private String validation_Code;
    private String validation_message;
}
