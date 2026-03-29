package com.t2ti.erp.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CpfCnpjFormatterTest {

    // CPF Format Tests
    @Test
    void shouldFormatCpf() {
        assertEquals("529.982.247-25", CpfCnpjFormatter.formatCpf("52998224725"));
    }

    @Test
    void shouldReturnOriginalWhenCpfInvalidLength() {
        assertEquals("1234", CpfCnpjFormatter.formatCpf("1234"));
    }

    @Test
    void shouldReturnNullWhenCpfNull() {
        assertNull(CpfCnpjFormatter.formatCpf(null));
    }

    @Test
    void shouldUnformatCpf() {
        assertEquals("52998224725", CpfCnpjFormatter.unformatCpf("529.982.247-25"));
    }

    @Test
    void shouldUnformatAlreadyPlainCpf() {
        assertEquals("52998224725", CpfCnpjFormatter.unformatCpf("52998224725"));
    }

    @Test
    void shouldReturnNullWhenUnformatCpfNull() {
        assertNull(CpfCnpjFormatter.unformatCpf(null));
    }

    // CNPJ Format Tests
    @Test
    void shouldFormatCnpj() {
        assertEquals("11.222.333/0001-81", CpfCnpjFormatter.formatCnpj("11222333000181"));
    }

    @Test
    void shouldReturnOriginalWhenCnpjInvalidLength() {
        assertEquals("12345", CpfCnpjFormatter.formatCnpj("12345"));
    }

    @Test
    void shouldReturnNullWhenCnpjNull() {
        assertNull(CpfCnpjFormatter.formatCnpj(null));
    }

    @Test
    void shouldUnformatCnpj() {
        assertEquals("11222333000181", CpfCnpjFormatter.unformatCnpj("11.222.333/0001-81"));
    }

    @Test
    void shouldUnformatAlreadyPlainCnpj() {
        assertEquals("11222333000181", CpfCnpjFormatter.unformatCnpj("11222333000181"));
    }

    @Test
    void shouldReturnNullWhenUnformatCnpjNull() {
        assertNull(CpfCnpjFormatter.unformatCnpj(null));
    }
}
