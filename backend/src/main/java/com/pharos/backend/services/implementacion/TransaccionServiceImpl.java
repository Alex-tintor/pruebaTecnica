package com.pharos.backend.services.implementacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pharos.backend.dtos.consumo.AnularTransaccionConsumoDTO;
import com.pharos.backend.dtos.consumo.CrearTransaccionConsumoDTO;
import com.pharos.backend.dtos.respuesta.TransaccionRespuestaDTO;
import com.pharos.backend.entitys.Tarjeta;
import com.pharos.backend.entitys.Transaccion;
import com.pharos.backend.enums.EstadoTarjetaEnum;
import com.pharos.backend.enums.EstadoTransaccionEnum;
import com.pharos.backend.exceptions.ExcepcionNegocio;
import com.pharos.backend.exceptions.RecursoNoEncontradoExcepcion;
import com.pharos.backend.repositorys.TarjetaRepository;
import com.pharos.backend.repositorys.TransaccionRepository;
import com.pharos.backend.services.interfaz.TransaccionService;
import com.pharos.backend.utils.GeneradorDeReferenciaUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final TarjetaRepository tarjetaRepository;

    @Override
    public TransaccionRespuestaDTO crearTransaccion(CrearTransaccionConsumoDTO crearTransaccionConsumoDTO) {
        Tarjeta tarjeta = tarjetaRepository.obtenerTarjetaPorIdentificadorSinAnulacion(crearTransaccionConsumoDTO.getIdentificadorTarjeta())
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Tarjeta no encontrada"));
        if (!EstadoTarjetaEnum.ENROLADA.equals(tarjeta.getEstado())) {
            throw new ExcepcionNegocio("Tarjeta no enrolada");
        }
        Transaccion transaccion = new Transaccion();
        transaccion.setDireccionCompra(crearTransaccionConsumoDTO.getDireccionCompra());
        transaccion.setEstado(EstadoTransaccionEnum.APROBADA);
        transaccion.setIdentificador(GeneradorDeReferenciaUtil.generarSeisDigitos());
        transaccion.setMonto(crearTransaccionConsumoDTO.getMonto());
        transaccion.setTarjeta(tarjeta);
    
        return aRespuestaDTO(transaccionRepository.save(transaccion));
    }

    @Override
    public TransaccionRespuestaDTO anular(AnularTransaccionConsumoDTO anularTransaccionConsumoDTO) {
        Tarjeta tarjeta = tarjetaRepository.obtenerTarjetaPorIdentificadorSinAnulacion(anularTransaccionConsumoDTO.getIdentificadorTarjeta())
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Tarjeta no encontrada"));
        Transaccion transaccion = transaccionRepository.busacarPorReferencia(anularTransaccionConsumoDTO.getNumeroReferencia())
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Transaccion no encontrada"));
        if (!transaccion.getTarjeta().getId().equals(tarjeta.getId())) {
            throw new ExcepcionNegocio("No se cumple con la consistencia de los datos, contacte al administrador");
        }

        long minutos = ChronoUnit.MINUTES.between(transaccion.getFechaCreacion(), LocalDate.now());
        if(minutos > 5){
            throw new ExcepcionNegocio("La transaccion ha superado el tiempo limite para ser anulada.");
        }

        transaccion.setEstado(EstadoTransaccionEnum.ANULADA);
        transaccion.setFechaAnulacion(LocalDateTime.now());

        return aRespuestaDTO(transaccionRepository.save(transaccion));
    }

    @Override
    public List<TransaccionRespuestaDTO> consultarTodas() {
        return transaccionRepository.findAll().stream().map(this::aRespuestaDTO).toList();
    }

    private TransaccionRespuestaDTO aRespuestaDTO(Transaccion transaccion){
        return TransaccionRespuestaDTO.builder()
                .estadoTransaccion(transaccion.getEstado())
                .fechaAnulacion(transaccion.getFechaAnulacion())
                .fechaCreacion(transaccion.getFechaCreacion())
                .identificadorTarjeta(transaccion.getTarjeta().getIdentificador())
                .monto(transaccion.getMonto())
                .numeroReferencia(transaccion.getIdentificador())
                .build();
    }
    
}
