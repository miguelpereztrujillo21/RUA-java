package com.mpt.rua_java.data.remote.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO para información de la respuesta
 */
public class InfoDto {
    @SerializedName("seed")
    private String seed;

    @SerializedName("results")
    private int results;

    @SerializedName("page")
    private int page;

    @SerializedName("version")
    private String version;

    public String getSeed() { return seed; }
    public int getResults() { return results; }
    public int getPage() { return page; }
    public String getVersion() { return version; }
}
