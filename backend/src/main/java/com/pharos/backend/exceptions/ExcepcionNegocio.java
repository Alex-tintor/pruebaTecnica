package com.pharos.backend.exceptions;

public class ExcepcionNegocio extends RuntimeException {
    
    public ExcepcionNegocio(String mensaje){
        super(mensaje);
    }
}
