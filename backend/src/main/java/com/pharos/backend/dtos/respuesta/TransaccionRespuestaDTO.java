package com.pharos.backend.dtos.respuesta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.pharos.backend.enums.EstadoTransaccionEnum;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TransaccionRespuestaDTO {
    
    private String numeroReferencia;
    private String identificadorTarjeta;
    private BigDecimal monto;
    private String direccionCompra;
    private EstadoTransaccionEnum estadoTransaccion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaAnulacion;
}
