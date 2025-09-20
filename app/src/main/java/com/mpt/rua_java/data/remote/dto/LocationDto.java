package com.mpt.rua_java.data.remote.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO para la ubicación según API RandomUser
 */
public class LocationDto {
    @SerializedName("street")
    private StreetDto street;

    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;

    @SerializedName("country")
    private String country;

    @SerializedName("postcode")
    private String postcode;

    @SerializedName("coordinates")
    private CoordinatesDto coordinates;

    @SerializedName("timezone")
    private TimezoneDto timezone;

    public StreetDto getStreet() { return street; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getCountry() { return country; }
    public String getPostcode() { return postcode; }
    public CoordinatesDto getCoordinates() { return coordinates; }
    public TimezoneDto getTimezone() { return timezone; }
}
