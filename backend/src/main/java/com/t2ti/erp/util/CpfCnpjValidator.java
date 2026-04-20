package com.t2ti.erp.util;

/**
 * CPF and CNPJ validation using modulo 11 check-digit algorithm.
 */
public final class CpfCnpjValidator {

    private CpfCnpjValidator() {
    }

    /**
     * Validates a CPF number (Brazilian individual taxpayer ID).
     * Accepts both formatted (###.###.###-##) and unformatted (###########) input.
     *
     * @param cpf the CPF string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidCpf(String cpf) {
        if (cpf == null) {
            return false;
        }

        String digits = cpf.replaceAll("[^0-9]", "");

        if (digits.length() != 11) {
            return false;
        }

        if (digits.chars().distinct().count() == 1) {
            return false;
        }

        String base = digits.substring(0, 9);
        int firstDigit = Modulo11.calculate(base, 11);
        int secondDigit = Modulo11.calculate(base + firstDigit, 11);

        return digits.equals(base + firstDigit + secondDigit);
    }

    /**
     * Validates a CNPJ number (Brazilian company taxpayer ID).
     * Accepts both formatted (##.###.###/####-##) and unformatted (##############) input.
     *
     * @param cnpj the CNPJ string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidCnpj(String cnpj) {
        if (cnpj == null) {
            return false;
        }

        String digits = cnpj.replaceAll("[^0-9]", "");

        if (digits.length() != 14) {
            return false;
        }

        if (digits.chars().distinct().count() == 1) {
            return false;
        }

        String base = digits.substring(0, 12);
        int firstDigit = Modulo11.calculate(base, 9);
        int secondDigit = Modulo11.calculate(base + firstDigit, 9);

        return digits.equals(base + firstDigit + secondDigit);
    }
}
