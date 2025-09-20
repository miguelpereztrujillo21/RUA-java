package com.mpt.rua_java.data.remote.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * DTO principal para la respuesta de la API RandomUser
 * Mapea directamente la estructura JSON según documentación oficial
 * https://randomuser.me/documentation
 */
public class RandomUserResponseDto {
    @SerializedName("results")
    private List<UserDto> results;

    @SerializedName("info")
    private InfoDto info;

    public List<UserDto> getResults() { return results; }
    public InfoDto getInfo() { return info; }
}
