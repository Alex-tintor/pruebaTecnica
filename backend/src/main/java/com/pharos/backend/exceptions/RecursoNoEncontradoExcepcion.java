package com.pharos.backend.exceptions;

public class RecursoNoEncontradoExcepcion extends RuntimeException {
    
    public RecursoNoEncontradoExcepcion(String mensaje){
        super(mensaje);
    }
}
