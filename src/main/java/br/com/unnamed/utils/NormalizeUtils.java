package br.com.unnamed.utils;

public class NormalizeUtils {

    public static String onlyNumbers(String s) {
        return s.replaceAll("[^0-9]+", "");
    }


    //TODO: Implementar metodos para exibir dados formatados
    public static String formatToPhone(String s) {
        return s;
    }

    public static String formatToCPF(String s) {
        return s;
    }
}
