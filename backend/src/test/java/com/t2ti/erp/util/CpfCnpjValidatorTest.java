package com.t2ti.erp.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CpfCnpjValidatorTest {

    // CPF Tests
    @Test
    void shouldValidateValidCpf() {
        assertTrue(CpfCnpjValidator.isValidCpf("52998224725"));
    }

    @Test
    void shouldValidateFormattedCpf() {
        assertTrue(CpfCnpjValidator.isValidCpf("529.982.247-25"));
    }

    @Test
    void shouldRejectInvalidCpf() {
        assertFalse(CpfCnpjValidator.isValidCpf("52998224700"));
    }

    @Test
    void shouldRejectNullCpf() {
        assertFalse(CpfCnpjValidator.isValidCpf(null));
    }

    @Test
    void shouldRejectEmptyCpf() {
        assertFalse(CpfCnpjValidator.isValidCpf(""));
    }

    @Test
    void shouldRejectAllZerosCpf() {
        assertFalse(CpfCnpjValidator.isValidCpf("00000000000"));
    }

    @Test
    void shouldRejectAllSameDigitsCpf() {
        assertFalse(CpfCnpjValidator.isValidCpf("11111111111"));
        assertFalse(CpfCnpjValidator.isValidCpf("22222222222"));
        assertFalse(CpfCnpjValidator.isValidCpf("99999999999"));
    }

    @Test
    void shouldRejectCpfWithWrongLength() {
        assertFalse(CpfCnpjValidator.isValidCpf("1234567890"));
        assertFalse(CpfCnpjValidator.isValidCpf("123456789012"));
    }

    @Test
    void shouldValidateAnotherValidCpf() {
        assertTrue(CpfCnpjValidator.isValidCpf("39053344705"));
    }

    // CNPJ Tests
    @Test
    void shouldValidateValidCnpj() {
        assertTrue(CpfCnpjValidator.isValidCnpj("11222333000181"));
    }

    @Test
    void shouldValidateFormattedCnpj() {
        assertTrue(CpfCnpjValidator.isValidCnpj("11.222.333/0001-81"));
    }

    @Test
    void shouldRejectInvalidCnpj() {
        assertFalse(CpfCnpjValidator.isValidCnpj("11222333000100"));
    }

    @Test
    void shouldRejectNullCnpj() {
        assertFalse(CpfCnpjValidator.isValidCnpj(null));
    }

    @Test
    void shouldRejectEmptyCnpj() {
        assertFalse(CpfCnpjValidator.isValidCnpj(""));
    }

    @Test
    void shouldRejectAllZerosCnpj() {
        assertFalse(CpfCnpjValidator.isValidCnpj("00000000000000"));
    }

    @Test
    void shouldRejectAllSameDigitsCnpj() {
        assertFalse(CpfCnpjValidator.isValidCnpj("11111111111111"));
    }

    @Test
    void shouldRejectCnpjWithWrongLength() {
        assertFalse(CpfCnpjValidator.isValidCnpj("1122233300018"));
        assertFalse(CpfCnpjValidator.isValidCnpj("112223330001811"));
    }
}
