package com.mpt.rua_java.data.remote.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO para fecha de registro
 */
public class RegisteredDto {
    @SerializedName("date")
    private String date;

    @SerializedName("age")
    private int age;

    public String getDate() { return date; }
    public int getAge() { return age; }
}
