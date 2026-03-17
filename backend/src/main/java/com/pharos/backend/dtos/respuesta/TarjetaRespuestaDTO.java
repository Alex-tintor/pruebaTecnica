package com.pharos.backend.dtos.respuesta;

import com.pharos.backend.enums.EstadoTarjetaEnum;
import com.pharos.backend.enums.TipoTarjetaEnum;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TarjetaRespuestaDTO {

    private String identificador;
    private String panMascara;
    private String nombreTitular;
    private String numeroDocumento;
    private TipoTarjetaEnum tipoTarjeta;
    private String telefono;
    private Integer numeroValidacion;
    private EstadoTarjetaEnum estadoTarjeta;
}
