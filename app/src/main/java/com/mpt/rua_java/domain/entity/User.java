package com.mpt.rua_java.domain.entity;

/**
 * Entidad de dominio que representa un usuario
 * Núcleo del negocio sin dependencias externas
 */
public class User {
    private final String id;
    private final String gender;
    private final Name name;
    private final Location location;
    private final String email;
    private final Login login;
    private final DateOfBirth dob;
    private final Registered registered;
    private final String phone;
    private final String cell;
    private final Picture picture;
    private final String nat;
    private final boolean isAddedToContacts;

    public User(String id, String gender, Name name, Location location, String email,
                Login login, DateOfBirth dob, Registered registered, String phone,
                String cell, Picture picture, String nat, boolean isAddedToContacts) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.location = location;
        this.email = email;
        this.login = login;
        this.dob = dob;
        this.registered = registered;
        this.phone = phone;
        this.cell = cell;
        this.picture = picture;
        this.nat = nat;
        this.isAddedToContacts = isAddedToContacts;
    }

    public String getFullName() {
        return name.getTitle() + " " + name.getFirst() + " " + name.getLast();
    }

    public String getFullAddress() {
        return location.getStreet().getNumber() + " " + location.getStreet().getName() + ", " +
               location.getCity() + ", " + location.getState() + ", " + location.getCountry() +
               " " + location.getPostcode();
    }

    /**
     * Genera datos vCard para QR funcional
     */
    public String getVCardData() {
        return "BEGIN:VCARD\n" +
               "VERSION:3.0\n" +
               "FN:" + getFullName() + "\n" +
               "N:" + name.getLast() + ";" + name.getFirst() + ";;" + name.getTitle() + ";\n" +
               "EMAIL:" + email + "\n" +
               "TEL;TYPE=CELL:" + cell + "\n" +
               "TEL;TYPE=VOICE:" + phone + "\n" +
               "ADR;TYPE=HOME:;;" + location.getStreet().getNumber() + " " + location.getStreet().getName() +
               ";" + location.getCity() + ";" + location.getState() + ";" + location.getPostcode() +
               ";" + location.getCountry() + "\n" +
               "BDAY:" + dob.getDate().substring(0, 10) + "\n" +
               "GENDER:" + gender.toUpperCase() + "\n" +
               "END:VCARD";
    }

    // Getters
    public String getId() { return id; }
    public String getGender() { return gender; }
    public Name getName() { return name; }
    public Location getLocation() { return location; }
    public String getEmail() { return email; }
    public Login getLogin() { return login; }
    public DateOfBirth getDob() { return dob; }
    public Registered getRegistered() { return registered; }
    public String getPhone() { return phone; }
    public String getCell() { return cell; }
    public Picture getPicture() { return picture; }
    public String getNat() { return nat; }
    public boolean isAddedToContacts() { return isAddedToContacts; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
