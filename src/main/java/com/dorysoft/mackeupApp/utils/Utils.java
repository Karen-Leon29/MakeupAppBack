package com.dorysoft.mackeupApp.utils;

import java.util.Random;

public class Utils {
    public static String generarCodigo(int longitud) {
        if (longitud <= 0 || longitud > 6) {
            throw new IllegalArgumentException("La longitud debe ser entre 1 y 6.");
        }

        Random random = new Random();
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            int numero = random.nextInt(10); // Genera un número entre 0 y 9
            codigo.append(numero);
        }

        return codigo.toString();
    }
}
