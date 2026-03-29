package com.t2ti.erp.util;

/**
 * Formats and unformats CPF and CNPJ strings.
 */
public final class CpfCnpjFormatter {

    private CpfCnpjFormatter() {
    }

    /**
     * Formats a CPF string to ###.###.###-## pattern.
     *
     * @param cpf the unformatted CPF (11 digits)
     * @return the formatted CPF, or the original string if invalid length
     */
    public static String formatCpf(String cpf) {
        if (cpf == null) {
            return null;
        }
        String digits = cpf.replaceAll("[^0-9]", "");
        if (digits.length() != 11) {
            return cpf;
        }
        return digits.substring(0, 3) + "." +
               digits.substring(3, 6) + "." +
               digits.substring(6, 9) + "-" +
               digits.substring(9, 11);
    }

    /**
     * Removes formatting from a CPF string.
     *
     * @param cpf the formatted CPF
     * @return the unformatted CPF (digits only)
     */
    public static String unformatCpf(String cpf) {
        if (cpf == null) {
            return null;
        }
        return cpf.replaceAll("[^0-9]", "");
    }

    /**
     * Formats a CNPJ string to ##.###.###/####-## pattern.
     *
     * @param cnpj the unformatted CNPJ (14 digits)
     * @return the formatted CNPJ, or the original string if invalid length
     */
    public static String formatCnpj(String cnpj) {
        if (cnpj == null) {
            return null;
        }
        String digits = cnpj.replaceAll("[^0-9]", "");
        if (digits.length() != 14) {
            return cnpj;
        }
        return digits.substring(0, 2) + "." +
               digits.substring(2, 5) + "." +
               digits.substring(5, 8) + "/" +
               digits.substring(8, 12) + "-" +
               digits.substring(12, 14);
    }

    /**
     * Removes formatting from a CNPJ string.
     *
     * @param cnpj the formatted CNPJ
     * @return the unformatted CNPJ (digits only)
     */
    public static String unformatCnpj(String cnpj) {
        if (cnpj == null) {
            return null;
        }
        return cnpj.replaceAll("[^0-9]", "");
    }
}
