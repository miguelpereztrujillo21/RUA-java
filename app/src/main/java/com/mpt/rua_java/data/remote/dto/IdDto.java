package com.mpt.rua_java.data.remote.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO para ID del usuario
 */
public class IdDto {
    @SerializedName("name")
    private String name;

    @SerializedName("value")
    private String value;

    public String getName() { return name; }
    public String getValue() { return value; }
}
