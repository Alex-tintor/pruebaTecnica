package com.pharos.backend.services.interfaz;

import java.util.List;

import com.pharos.backend.dtos.consumo.AnularTransaccionConsumoDTO;
import com.pharos.backend.dtos.consumo.CrearTransaccionConsumoDTO;
import com.pharos.backend.dtos.respuesta.TransaccionRespuestaDTO;

public interface TransaccionService {
    
    public TransaccionRespuestaDTO crearTransaccion(CrearTransaccionConsumoDTO crearTransaccionConsumoDTO);

    public TransaccionRespuestaDTO anular(AnularTransaccionConsumoDTO anularTransaccionConsumoDTO);

    public List<TransaccionRespuestaDTO> consultarTodas();
}
