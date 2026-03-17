package com.pharos.backend.services.implementacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pharos.backend.dtos.consumo.CrearTarjetaConsumoDTO;
import com.pharos.backend.dtos.consumo.EnrolarTarjetaConsumoDTO;
import com.pharos.backend.dtos.respuesta.TarjetaRespuestaDTO;
import com.pharos.backend.entitys.Tarjeta;
import com.pharos.backend.enums.EstadoTarjetaEnum;
import com.pharos.backend.exceptions.ExcepcionNegocio;
import com.pharos.backend.exceptions.RecursoNoEncontradoExcepcion;
import com.pharos.backend.repositorys.TarjetaRepository;
import com.pharos.backend.services.interfaz.TarjetaService;
import com.pharos.backend.utils.EnmascararTarjetaUtil;
import com.pharos.backend.utils.HashUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TarjetaServiceImpl implements TarjetaService{
    
    private final TarjetaRepository tarjetaRepository;

    @Override
    public TarjetaRespuestaDTO crear(CrearTarjetaConsumoDTO crearTarjetaConsumoDTO) {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setIdentificador(HashUtil.sha256(crearTarjetaConsumoDTO.getPan()+LocalDate.now()));
        tarjeta.setPan(crearTarjetaConsumoDTO.getPan());
        tarjeta.setNombreTitular(crearTarjetaConsumoDTO.getNombreTitular());
        tarjeta.setNumeroDocumento(crearTarjetaConsumoDTO.getNumeroDocumento());
        tarjeta.setTipoTarjeta(crearTarjetaConsumoDTO.getTipoTarjeta());
        tarjeta.setTelefono(crearTarjetaConsumoDTO.getTelefono());
        tarjeta.setNumeroValidacion(ThreadLocalRandom.current().nextInt(1,101));
        tarjeta.setEstado(EstadoTarjetaEnum.CREADA);
        tarjeta = tarjetaRepository.save(tarjeta);

        return aRespuesta(tarjeta);
    }

    @Override
    public TarjetaRespuestaDTO enrolar(String identificador,EnrolarTarjetaConsumoDTO enrolarTarjetaConsumoDTO) {
        Tarjeta tarjeta = obtenerTarjetaActiva(identificador);
        System.out.println(tarjeta.getIdentificador()+" espacioooo "+tarjeta.getNumeroValidacion());
        if(!tarjeta.getNumeroValidacion().equals(enrolarTarjetaConsumoDTO.getNumeroDeValidacion())){
            throw new ExcepcionNegocio("Numero de validacion invalido");
        }
        tarjeta.setEstado(EstadoTarjetaEnum.ENROLADA);
        return aRespuesta(tarjeta);
    }

    @Override
    public TarjetaRespuestaDTO consultarPorIdentificador(String identificador) {
        return aRespuesta(obtenerTarjetaActiva(identificador));
    }

    @Override
    public void inactivarTarjeta(String identificador) {
        Tarjeta tarjeta = obtenerTarjetaActiva(identificador);
        tarjeta.setEstado(EstadoTarjetaEnum.INACTIVA);
        tarjeta.setFechaInactivacion(LocalDateTime.now());
        tarjetaRepository.save(tarjeta);
    }

    @Override
    public List<TarjetaRespuestaDTO> consultarTodas() {
        return tarjetaRepository.findAll().stream().filter(c -> c.getFechaInactivacion() == null).map(this::aRespuesta).toList();
    }

    private Tarjeta obtenerTarjetaActiva(String identificador){
        return tarjetaRepository.obtenerTarjetaPorIdentificadorSinAnulacion(identificador).orElseThrow(() -> new RecursoNoEncontradoExcepcion("Tarjeta no encontrada"));
    }

    private TarjetaRespuestaDTO aRespuesta(Tarjeta tarjeta){
        return TarjetaRespuestaDTO.builder()
                    .identificador(tarjeta.getIdentificador())
                    .panMascara(EnmascararTarjetaUtil.enmascararPan(tarjeta.getPan()))
                    .nombreTitular(tarjeta.getNombreTitular())
                    .numeroDocumento(tarjeta.getNumeroDocumento())
                    .tipoTarjeta(tarjeta.getTipoTarjeta())
                    .telefono(tarjeta.getTelefono())
                    .numeroValidacion(tarjeta.getNumeroValidacion())
                    .estadoTarjeta(tarjeta.getEstado())
                    .build();
    }

}
