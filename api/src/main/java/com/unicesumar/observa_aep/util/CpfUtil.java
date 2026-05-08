package com.unicesumar.observa_aep.util;

public class CpfUtil {

    private CpfUtil() {
    }

    public static String removerMascara(String cpf) {
        if (cpf == null) return null;
        return cpf.replaceAll("[^0-9]", "");
    }
}