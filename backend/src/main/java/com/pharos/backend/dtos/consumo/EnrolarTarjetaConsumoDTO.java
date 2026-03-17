package com.pharos.backend.dtos.consumo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrolarTarjetaConsumoDTO {

    @NotNull
    @Min(1)
    @Max(100)
    private Integer numeroDeValidacion;
    
}
