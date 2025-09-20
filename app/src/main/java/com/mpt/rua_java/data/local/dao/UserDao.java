package com.mpt.rua_java.data.local.dao;

import androidx.room.*;
import com.mpt.rua_java.data.local.entity.UserEntity;
import java.util.List;

/**
 * DAO para operaciones de base de datos con usuarios
 * Proporciona métodos para CRUD y consultas específicas
 * Implementa las operaciones necesarias para los requisitos del proyecto
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM users ORDER BY nameFirst ASC")
    List<UserEntity> getAllUsers();

    @Query("SELECT * FROM users WHERE id = :id")
    UserEntity getUserById(String id);

    @Query("SELECT * FROM users WHERE gender = :gender ORDER BY nameFirst ASC")
    List<UserEntity> getUsersByGender(String gender);

    @Query("SELECT * FROM users WHERE nat = :nationality ORDER BY nameFirst ASC")
    List<UserEntity> getUsersByNationality(String nationality);

    @Query("SELECT * FROM users " +
           "WHERE nameFirst LIKE '%' || :query || '%' " +
           "OR nameLast LIKE '%' || :query || '%' " +
           "OR email LIKE '%' || :query || '%' " +
           "ORDER BY nameFirst ASC")
    List<UserEntity> searchUsersByName(String query);

    @Query("UPDATE users SET isAddedToContacts = :isAddedToContacts WHERE id = :userId")
    void updateUserContactStatus(String userId, boolean isAddedToContacts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<UserEntity> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity user);

    @Update
    void updateUser(UserEntity user);

    @Delete
    void deleteUser(UserEntity user);

    @Query("DELETE FROM users")
    void deleteAllUsers();

    @Query("SELECT COUNT(*) FROM users")
    int getUserCount();

    @Query("SELECT * FROM users WHERE isAddedToContacts = 1 ORDER BY nameFirst ASC")
    List<UserEntity> getContactUsers();
}
