package com.mpt.rua_java.data.remote.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO para la calle
 */
public class StreetDto {
    @SerializedName("number")
    private int number;

    @SerializedName("name")
    private String name;

    public int getNumber() { return number; }
    public String getName() { return name; }
}
