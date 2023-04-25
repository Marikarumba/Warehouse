package com.skypro.warehouse.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Validated
@Data
public class SocksListDto {

        @NotBlank
        private String color;


        @NotBlank
        private  SocksOperations operation; // {moreThan, lessThan, equal}; //оператор сравнения значения количества хлопка в составе носков, одно значение из: moreThan, lessThan, equal
        @NotBlank
        @Min(value = 1)
        @Max(value = 100)
        private int cottonPart;

}
