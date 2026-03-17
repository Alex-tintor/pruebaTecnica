package com.pharos.backend.utils;

import java.util.concurrent.ThreadLocalRandom;

public class GeneradorDeReferenciaUtil {
    
    public static String generarSeisDigitos(){
        int valor = ThreadLocalRandom.current().nextInt(100000, 1000000);
        return String.valueOf(valor);
    }
}
