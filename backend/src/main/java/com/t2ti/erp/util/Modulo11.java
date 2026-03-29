package com.t2ti.erp.util;

/**
 * Generic Modulo 11 calculator used for CPF, CNPJ, and NF-e check digit validation.
 */
public final class Modulo11 {

    private Modulo11() {
    }

    /**
     * Calculates the modulo 11 check digit for the given numeric string.
     * Uses weights cycling from 2 to maxWeight, applied right-to-left.
     *
     * @param number    the numeric string
     * @param maxWeight the maximum weight in the cycle (9 for CPF, 9 for CNPJ)
     * @return the check digit (0-9)
     */
    public static int calculate(String number, int maxWeight) {
        int sum = 0;
        int weight = 2;

        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));
            sum += digit * weight;
            weight++;
            if (weight > maxWeight) {
                weight = 2;
            }
        }

        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }
}
