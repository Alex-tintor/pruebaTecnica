package com.pharos.backend.services.interfaz;

import java.util.List;

import com.pharos.backend.dtos.consumo.CrearTarjetaConsumoDTO;
import com.pharos.backend.dtos.consumo.EnrolarTarjetaConsumoDTO;
import com.pharos.backend.dtos.respuesta.TarjetaRespuestaDTO;

public interface TarjetaService {

    public TarjetaRespuestaDTO crear(CrearTarjetaConsumoDTO crearTarjetaConsumoDTO);

    public TarjetaRespuestaDTO enrolar(String identificador,EnrolarTarjetaConsumoDTO enrolarTarjetaConsumoDTO);

    public TarjetaRespuestaDTO consultarPorIdentificador(String identificador);

    public void inactivarTarjeta(String identificador);

    public List<TarjetaRespuestaDTO> consultarTodas();
}
