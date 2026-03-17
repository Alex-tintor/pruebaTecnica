package com.pharos.backend.dtos.consumo;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnularTransaccionConsumoDTO {
    
    @NotBlank
    private String identificadorTarjeta;

    @NotBlank
    private String numeroReferencia;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal monto;
}
