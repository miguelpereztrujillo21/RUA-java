package com.mpt.rua_java.domain.entity;

/**
 * Entidad de dominio para la calle
 */
public class Street {
    private final int number;
    private final String name;

    public Street(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() { return number; }
    public String getName() { return name; }
}
