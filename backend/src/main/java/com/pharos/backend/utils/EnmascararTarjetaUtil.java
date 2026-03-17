package com.pharos.backend.utils;

public class EnmascararTarjetaUtil {
    
    public static String enmascararPan(String pan){
        if (pan == null || pan.length() < 10) {
            return pan;
        }

        String primerSeis = pan.substring(0, 6);
        String ultimosCuatro = pan.substring(pan.length() - 4);
        String seccionEnmascarada = "*".repeat(pan.length() - 10);

        return primerSeis + seccionEnmascarada + ultimosCuatro;
    }
}
