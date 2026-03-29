package com.t2ti.erp.security;

public final class TenantContext {

    private static final ThreadLocal<Long> CURRENT_EMPRESA_ID = new ThreadLocal<>();

    private TenantContext() {
    }

    public static void setEmpresaId(Long empresaId) {
        CURRENT_EMPRESA_ID.set(empresaId);
    }

    public static Long getEmpresaId() {
        return CURRENT_EMPRESA_ID.get();
    }

    public static void clear() {
        CURRENT_EMPRESA_ID.remove();
    }
}
