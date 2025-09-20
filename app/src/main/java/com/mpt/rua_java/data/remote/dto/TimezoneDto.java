package com.mpt.rua_java.data.remote.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO para la zona horaria
 */
public class TimezoneDto {
    @SerializedName("offset")
    private String offset;

    @SerializedName("description")
    private String description;

    public String getOffset() { return offset; }
    public String getDescription() { return description; }
}
