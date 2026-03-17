package com.pharos.backend.dtos.consumo;

import com.pharos.backend.enums.TipoTarjetaEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CrearTarjetaConsumoDTO {
    
    @NotBlank
    @Pattern(regexp = "\\d{16,19}", message = "El PAN debe de contener entre 16 y 19 digitos")
    private String pan;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nombreTitular;

    @NotBlank
    @Size(min = 10, max = 15, message = "El numero de documento del titular debe contener entre 10 y 15 digitos")
    private String numeroDocumento;

    @NotNull
    private TipoTarjetaEnum tipoTarjeta;

    @NotBlank
    @Pattern(regexp = "\\d{10}",message = "El numero de telefono no puede contener mas de 10 digitos")
    private String telefono;
}
