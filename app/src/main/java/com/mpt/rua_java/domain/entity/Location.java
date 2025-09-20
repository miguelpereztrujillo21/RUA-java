package com.mpt.rua_java.domain.entity;

/**
 * Entidad de dominio para la ubicación de un usuario
 */
public class Location {
    private final Street street;
    private final String city;
    private final String state;
    private final String country;
    private final String postcode;
    private final Coordinates coordinates;
    private final Timezone timezone;

    public Location(Street street, String city, String state, String country,
                   String postcode, Coordinates coordinates, Timezone timezone) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postcode = postcode;
        this.coordinates = coordinates;
        this.timezone = timezone;
    }

    public Street getStreet() { return street; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getCountry() { return country; }
    public String getPostcode() { return postcode; }
    public Coordinates getCoordinates() { return coordinates; }
    public Timezone getTimezone() { return timezone; }
}
