package com.mpt.rua_java.data.remote.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO para las coordenadas
 */
public class CoordinatesDto {
    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    public String getLatitude() { return latitude; }
    public String getLongitude() { return longitude; }
}
