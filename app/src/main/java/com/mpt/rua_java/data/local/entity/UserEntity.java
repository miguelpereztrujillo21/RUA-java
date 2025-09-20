package com.mpt.rua_java.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entidad Room para la persistencia local de usuarios
 * Representa la tabla de usuarios en SQLite
 * Diseñada para almacenar los 100 usuarios según requisitos
 */
@Entity(tableName = "users")
public class UserEntity {
    @PrimaryKey
    @NonNull
    private String id;
    private String gender;
    private String nameTitle;
    private String nameFirst;
    private String nameLast;
    private int streetNumber;
    private String streetName;
    private String city;
    private String state;
    private String country;
    private String postcode;
    private String coordinatesLatitude;
    private String coordinatesLongitude;
    private String timezoneOffset;
    private String timezoneDescription;
    private String email;
    private String loginUuid;
    private String loginUsername;
    private String loginPassword;
    private String loginSalt;
    private String loginMd5;
    private String loginSha1;
    private String loginSha256;
    private String dobDate;
    private int dobAge;
    private String registeredDate;
    private int registeredAge;
    private String phone;
    private String cell;
    private String pictureLarge;
    private String pictureMedium;
    private String pictureThumbnail;
    private String nat;
    private boolean isAddedToContacts;

    //TODO Constructor vacío requerido por Room


    public UserEntity(String id, String gender, String nameTitle, String nameFirst, String nameLast,
                     int streetNumber, String streetName, String city, String state, String country,
                     String postcode, String coordinatesLatitude, String coordinatesLongitude,
                     String timezoneOffset, String timezoneDescription, String email,
                     String loginUuid, String loginUsername, String loginPassword, String loginSalt,
                     String loginMd5, String loginSha1, String loginSha256, String dobDate, int dobAge,
                     String registeredDate, int registeredAge, String phone, String cell,
                     String pictureLarge, String pictureMedium, String pictureThumbnail,
                     String nat, boolean isAddedToContacts) {
        this.id = id;
        this.gender = gender;
        this.nameTitle = nameTitle;
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postcode = postcode;
        this.coordinatesLatitude = coordinatesLatitude;
        this.coordinatesLongitude = coordinatesLongitude;
        this.timezoneOffset = timezoneOffset;
        this.timezoneDescription = timezoneDescription;
        this.email = email;
        this.loginUuid = loginUuid;
        this.loginUsername = loginUsername;
        this.loginPassword = loginPassword;
        this.loginSalt = loginSalt;
        this.loginMd5 = loginMd5;
        this.loginSha1 = loginSha1;
        this.loginSha256 = loginSha256;
        this.dobDate = dobDate;
        this.dobAge = dobAge;
        this.registeredDate = registeredDate;
        this.registeredAge = registeredAge;
        this.phone = phone;
        this.cell = cell;
        this.pictureLarge = pictureLarge;
        this.pictureMedium = pictureMedium;
        this.pictureThumbnail = pictureThumbnail;
        this.nat = nat;
        this.isAddedToContacts = isAddedToContacts;
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getNameTitle() { return nameTitle; }
    public void setNameTitle(String nameTitle) { this.nameTitle = nameTitle; }

    public String getNameFirst() { return nameFirst; }
    public void setNameFirst(String nameFirst) { this.nameFirst = nameFirst; }

    public String getNameLast() { return nameLast; }
    public void setNameLast(String nameLast) { this.nameLast = nameLast; }

    public int getStreetNumber() { return streetNumber; }
    public void setStreetNumber(int streetNumber) { this.streetNumber = streetNumber; }

    public String getStreetName() { return streetName; }
    public void setStreetName(String streetName) { this.streetName = streetName; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getPostcode() { return postcode; }
    public void setPostcode(String postcode) { this.postcode = postcode; }

    public String getCoordinatesLatitude() { return coordinatesLatitude; }
    public void setCoordinatesLatitude(String coordinatesLatitude) { this.coordinatesLatitude = coordinatesLatitude; }

    public String getCoordinatesLongitude() { return coordinatesLongitude; }
    public void setCoordinatesLongitude(String coordinatesLongitude) { this.coordinatesLongitude = coordinatesLongitude; }

    public String getTimezoneOffset() { return timezoneOffset; }
    public void setTimezoneOffset(String timezoneOffset) { this.timezoneOffset = timezoneOffset; }

    public String getTimezoneDescription() { return timezoneDescription; }
    public void setTimezoneDescription(String timezoneDescription) { this.timezoneDescription = timezoneDescription; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getLoginUuid() { return loginUuid; }
    public void setLoginUuid(String loginUuid) { this.loginUuid = loginUuid; }

    public String getLoginUsername() { return loginUsername; }
    public void setLoginUsername(String loginUsername) { this.loginUsername = loginUsername; }

    public String getLoginPassword() { return loginPassword; }
    public void setLoginPassword(String loginPassword) { this.loginPassword = loginPassword; }

    public String getLoginSalt() { return loginSalt; }
    public void setLoginSalt(String loginSalt) { this.loginSalt = loginSalt; }

    public String getLoginMd5() { return loginMd5; }
    public void setLoginMd5(String loginMd5) { this.loginMd5 = loginMd5; }

    public String getLoginSha1() { return loginSha1; }
    public void setLoginSha1(String loginSha1) { this.loginSha1 = loginSha1; }

    public String getLoginSha256() { return loginSha256; }
    public void setLoginSha256(String loginSha256) { this.loginSha256 = loginSha256; }

    public String getDobDate() { return dobDate; }
    public void setDobDate(String dobDate) { this.dobDate = dobDate; }

    public int getDobAge() { return dobAge; }
    public void setDobAge(int dobAge) { this.dobAge = dobAge; }

    public String getRegisteredDate() { return registeredDate; }
    public void setRegisteredDate(String registeredDate) { this.registeredDate = registeredDate; }

    public int getRegisteredAge() { return registeredAge; }
    public void setRegisteredAge(int registeredAge) { this.registeredAge = registeredAge; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCell() { return cell; }
    public void setCell(String cell) { this.cell = cell; }

    public String getPictureLarge() { return pictureLarge; }
    public void setPictureLarge(String pictureLarge) { this.pictureLarge = pictureLarge; }

    public String getPictureMedium() { return pictureMedium; }
    public void setPictureMedium(String pictureMedium) { this.pictureMedium = pictureMedium; }

    public String getPictureThumbnail() { return pictureThumbnail; }
    public void setPictureThumbnail(String pictureThumbnail) { this.pictureThumbnail = pictureThumbnail; }

    public String getNat() { return nat; }
    public void setNat(String nat) { this.nat = nat; }

    public boolean isAddedToContacts() { return isAddedToContacts; }
    public void setAddedToContacts(boolean addedToContacts) { isAddedToContacts = addedToContacts; }
}
