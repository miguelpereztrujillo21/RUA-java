package com.mpt.rua_java.data.remote.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO para un usuario individual de la API RandomUser
 */
public class UserDto {
    @SerializedName("gender")
    private String gender;

    @SerializedName("name")
    private NameDto name;

    @SerializedName("location")
    private LocationDto location;

    @SerializedName("email")
    private String email;

    @SerializedName("login")
    private LoginDto login;

    @SerializedName("dob")
    private DateOfBirthDto dob;

    @SerializedName("registered")
    private RegisteredDto registered;

    @SerializedName("phone")
    private String phone;

    @SerializedName("cell")
    private String cell;

    @SerializedName("id")
    private IdDto id;

    @SerializedName("picture")
    private PictureDto picture;

    @SerializedName("nat")
    private String nat;


    public String getGender() { return gender; }
    public NameDto getName() { return name; }
    public LocationDto getLocation() { return location; }
    public String getEmail() { return email; }
    public LoginDto getLogin() { return login; }
    public DateOfBirthDto getDob() { return dob; }
    public RegisteredDto getRegistered() { return registered; }
    public String getPhone() { return phone; }
    public String getCell() { return cell; }
    public IdDto getId() { return id; }
    public PictureDto getPicture() { return picture; }
    public String getNat() { return nat; }


    public void setGender(String gender) { this.gender = gender; }
    public void setName(NameDto name) { this.name = name; }
    public void setLocation(LocationDto location) { this.location = location; }
    public void setEmail(String email) { this.email = email; }
    public void setLogin(LoginDto login) { this.login = login; }
    public void setDob(DateOfBirthDto dob) { this.dob = dob; }
    public void setRegistered(RegisteredDto registered) { this.registered = registered; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setCell(String cell) { this.cell = cell; }
    public void setId(IdDto id) { this.id = id; }
    public void setPicture(PictureDto picture) { this.picture = picture; }
    public void setNat(String nat) { this.nat = nat; }
}
