package com.mpt.rua_java.domain.entity;

/**
 * Entidad de dominio para las coordenadas geográficas
 */
public class Coordinates {
    private final String latitude;
    private final String longitude;

    public Coordinates(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() { return latitude; }
    public String getLongitude() { return longitude; }
}
