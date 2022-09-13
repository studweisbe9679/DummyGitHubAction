package com.example.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AreaResponseDTO {

    private String name;
    private int decimals;
    private double area;

}
