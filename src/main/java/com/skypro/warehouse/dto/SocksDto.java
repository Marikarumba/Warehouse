package com.skypro.warehouse.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class SocksDto {
    @NotBlank
    private String color;
    @NotBlank
    @Min(value = 1)
    @Max(value = 100)
    private int cottonPart;
    @NotBlank
    @Min(value = 1)
    private int quantity;
}
