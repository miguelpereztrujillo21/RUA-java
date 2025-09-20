package com.mpt.rua_java.domain.entity;

/**
 * Entidad de dominio para el nombre de un usuario
 */
public class Name {
    private final String title;
    private final String first;
    private final String last;

    public Name(String title, String first, String last) {
        this.title = title;
        this.first = first;
        this.last = last;
    }

    public String getTitle() { return title; }
    public String getFirst() { return first; }
    public String getLast() { return last; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return title.equals(name.title) && first.equals(name.first) && last.equals(name.last);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(title, first, last);
    }
}
