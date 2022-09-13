package com.example.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PerimeterResponseDTO {

    private String name;
    private int decimals;
    private double perimeter;

}
