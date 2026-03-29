package com.t2ti.erp.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Modulo11Test {

    @Test
    void shouldCalculateCheckDigitForCpfFirstDigit() {
        // CPF 529.982.247-25 -> base "529982247"
        // First check digit should be 2
        int result = Modulo11.calculate("529982247", 11);
        assertEquals(2, result);
    }

    @Test
    void shouldCalculateCheckDigitForCpfSecondDigit() {
        // CPF 529.982.247-25 -> base + first digit "5299822472"
        // Second check digit should be 5
        int result = Modulo11.calculate("5299822472", 11);
        assertEquals(5, result);
    }

    @Test
    void shouldCalculateCheckDigitForCnpjFirstDigit() {
        // CNPJ 11.222.333/0001-81 -> base "112223330001"
        int result = Modulo11.calculate("112223330001", 9);
        assertEquals(8, result);
    }

    @Test
    void shouldCalculateCheckDigitForCnpjSecondDigit() {
        // CNPJ 11.222.333/0001-81 -> base + first digit "1122233300018"
        int result = Modulo11.calculate("1122233300018", 9);
        assertEquals(1, result);
    }

    @Test
    void shouldReturnZeroWhenRemainderLessThanTwo() {
        // When sum % 11 < 2, check digit should be 0
        int result = Modulo11.calculate("000000000", 11);
        assertEquals(0, result);
    }
}
