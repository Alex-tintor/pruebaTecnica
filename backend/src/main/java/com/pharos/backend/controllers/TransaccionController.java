package com.pharos.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pharos.backend.dtos.consumo.AnularTransaccionConsumoDTO;
import com.pharos.backend.dtos.consumo.CrearTransaccionConsumoDTO;
import com.pharos.backend.dtos.respuesta.TransaccionRespuestaDTO;
import com.pharos.backend.services.interfaz.TransaccionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/transacciones")
@RequiredArgsConstructor
public class TransaccionController {

    private final TransaccionService transaccionService;

    @PostMapping
    public TransaccionRespuestaDTO crear(@Valid @RequestBody CrearTransaccionConsumoDTO crearTransaccionConsumoDTO){
        return transaccionService.crearTransaccion(crearTransaccionConsumoDTO);
    }

    @PostMapping("/anular")
    public TransaccionRespuestaDTO anular(@Valid @RequestBody AnularTransaccionConsumoDTO anularTransaccionConsumoDTO){
        return transaccionService.anular(anularTransaccionConsumoDTO);
    }

    @GetMapping
    public List<TransaccionRespuestaDTO> consultarTodas(){
        return transaccionService.consultarTodas();
    }
    
}
