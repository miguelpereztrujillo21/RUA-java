package com.mpt.rua_java.domain.entity;

/**
 * Entidad de dominio para la zona horaria
 */
public class Timezone {
    private final String offset;
    private final String description;

    public Timezone(String offset, String description) {
        this.offset = offset;
        this.description = description;
    }

    public String getOffset() { return offset; }
    public String getDescription() { return description; }
}
