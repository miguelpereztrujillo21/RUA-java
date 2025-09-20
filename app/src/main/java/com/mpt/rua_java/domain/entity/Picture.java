package com.mpt.rua_java.domain.entity;

/**
 * Entidad de dominio para las imágenes de perfil
 */
public class Picture {
    private final String large;
    private final String medium;
    private final String thumbnail;

    public Picture(String large, String medium, String thumbnail) {
        this.large = large;
        this.medium = medium;
        this.thumbnail = thumbnail;
    }

    public String getLarge() { return large; }
    public String getMedium() { return medium; }
    public String getThumbnail() { return thumbnail; }
}
