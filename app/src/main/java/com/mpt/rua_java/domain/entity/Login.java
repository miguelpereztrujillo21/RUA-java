package com.mpt.rua_java.domain.entity;

/**
 * Entidad de dominio para la información de login
 */
public class Login {
    private final String uuid;
    private final String username;
    private final String password;
    private final String salt;
    private final String md5;
    private final String sha1;
    private final String sha256;

    public Login(String uuid, String username, String password, String salt,
                 String md5, String sha1, String sha256) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.md5 = md5;
        this.sha1 = sha1;
        this.sha256 = sha256;
    }

    public String getUuid() { return uuid; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getSalt() { return salt; }
    public String getMd5() { return md5; }
    public String getSha1() { return sha1; }
    public String getSha256() { return sha256; }
}
