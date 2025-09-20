package com.mpt.rua_java.data.remote.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO para el nombre según API RandomUser
 */
public class NameDto {
    @SerializedName("title")
    private String title;

    @SerializedName("first")
    private String first;

    @SerializedName("last")
    private String last;

    public String getTitle() { return title; }
    public String getFirst() { return first; }
    public String getLast() { return last; }
}
