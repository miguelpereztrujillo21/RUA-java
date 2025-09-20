package com.mpt.rua_java.domain.entity;

/**
 * Entidad de dominio para la fecha de registro
 */
public class Registered {
    private final String date;
    private final int age;

    public Registered(String date, int age) {
        this.date = date;
        this.age = age;
    }

    public String getDate() { return date; }
    public int getAge() { return age; }
}
