package com.mpt.rua_java.data.remote.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO para fecha de nacimiento
 */
public class DateOfBirthDto {
    @SerializedName("date")
    private String date;

    @SerializedName("age")
    private int age;

    public String getDate() { return date; }
    public int getAge() { return age; }
}
