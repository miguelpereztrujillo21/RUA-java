package com.mpt.rua_java.data.remote.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO para las imágenes de perfil
 */
public class PictureDto {
    @SerializedName("large")
    private String large;

    @SerializedName("medium")
    private String medium;

    @SerializedName("thumbnail")
    private String thumbnail;

    public String getLarge() { return large; }
    public String getMedium() { return medium; }
    public String getThumbnail() { return thumbnail; }
}
