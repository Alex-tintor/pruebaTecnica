package com.pharos.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharos.backend.dtos.consumo.CrearTarjetaConsumoDTO;
import com.pharos.backend.dtos.consumo.EnrolarTarjetaConsumoDTO;
import com.pharos.backend.dtos.respuesta.TarjetaRespuestaDTO;
import com.pharos.backend.services.interfaz.TarjetaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/tarjetas")
@RequiredArgsConstructor
public class TarjetaController {
    
    private final TarjetaService tarjetaService;

    @PostMapping
    public TarjetaRespuestaDTO crear(@Valid @RequestBody CrearTarjetaConsumoDTO crearTarjetaConsumoDTO){
        return tarjetaService.crear(crearTarjetaConsumoDTO);
    }

    @PutMapping("{identificador}/enrolar")
    public TarjetaRespuestaDTO enrolar(@PathVariable String identificador, @Valid @RequestBody EnrolarTarjetaConsumoDTO enrolarTarjetaConsumoDTO){
        return tarjetaService.enrolar(identificador, enrolarTarjetaConsumoDTO);
    }

    @GetMapping("/{identificador}")
    public TarjetaRespuestaDTO consultarPorIdentificador(@PathVariable String identificador){
        return tarjetaService.consultarPorIdentificador(identificador);
    }

    @DeleteMapping("/{identificador}")
    public void inactivarTarjeta(@PathVariable String identificador){
        tarjetaService.inactivarTarjeta(identificador);
    }

    @GetMapping
    public List<TarjetaRespuestaDTO> consultarTodas(){
        return tarjetaService.consultarTodas();
    }
}
