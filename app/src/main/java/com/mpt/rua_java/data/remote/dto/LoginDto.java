package com.mpt.rua_java.data.remote.dto;

import com.google.gson.annotations.SerializedName;

/**
 * DTO para información de login
 */
public class LoginDto {
    @SerializedName("uuid")
    private String uuid;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("salt")
    private String salt;

    @SerializedName("md5")
    private String md5;

    @SerializedName("sha1")
    private String sha1;

    @SerializedName("sha256")
    private String sha256;

    public String getUuid() { return uuid; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getSalt() { return salt; }
    public String getMd5() { return md5; }
    public String getSha1() { return sha1; }
    public String getSha256() { return sha256; }
}
