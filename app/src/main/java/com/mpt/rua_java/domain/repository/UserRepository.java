package com.mpt.rua_java.domain.repository;

import com.mpt.rua_java.domain.entity.User;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Interface del repositorio de usuarios en la capa de dominio
 * Define el contrato que debe implementar la capa de datos
 * Sigue el principio de inversión de dependencias de SOLID
 */
public interface UserRepository {

    /**
     * Obtiene usuarios de la API RandomUser
     * Sigue la documentación oficial: https://randomuser.me/documentation
     * @param results número de usuarios a obtener (1-5000)
     * @param gender filtro por género: "male", "female" o null
     * @param nat nacionalidad (AU, BR, CA, CH, DE, DK, ES, FI, FR, GB, IE, IR, NO, NL, NZ, TR, US)
     * @param seed semilla para reproducir resultados
     * @return CompletableFuture con lista de usuarios
     */
    CompletableFuture<List<User>> getRandomUsers(int results, String gender, String nat, String seed);

    /**
     * Guarda usuarios en la base de datos local
     * @param users lista de usuarios a guardar
     * @return CompletableFuture que indica éxito o fallo
     */
    CompletableFuture<Void> saveUsers(List<User> users);

    /**
     * Obtiene todos los usuarios guardados localmente
     * @return CompletableFuture con lista de usuarios
     */
    CompletableFuture<List<User>> getAllUsers();

    /**
     * Obtiene un usuario por su ID
     * @param id identificador único del usuario
     * @return CompletableFuture con el usuario o null si no existe
     */
    CompletableFuture<User> getUserById(String id);

    /**
     * Actualiza el estado de contacto de un usuario
     * @param userId identificador del usuario
     * @param isAddedToContacts nuevo estado del contacto
     * @return CompletableFuture que indica éxito o fallo
     */
    CompletableFuture<Void> updateUserContactStatus(String userId, boolean isAddedToContacts);

    /**
     * Obtiene todos los usuarios que están marcados como contactos de la agenda
     * @return CompletableFuture con lista de usuarios en contactos
     */
    CompletableFuture<List<User>> getContactUsers();

    /**
     * Obtiene usuarios filtrados por género
     * @param gender género a filtrar
     * @return CompletableFuture con lista de usuarios filtrados
     */
    CompletableFuture<List<User>> getUsersByGender(String gender);

    /**
     * Obtiene usuarios filtrados por nacionalidad
     * @param nationality nacionalidad a filtrar
     * @return CompletableFuture con lista de usuarios filtrados
     */
    CompletableFuture<List<User>> getUsersByNationality(String nationality);

    /**
     * Busca usuarios por nombre
     * @param query consulta de búsqueda
     * @return CompletableFuture con lista de usuarios que coinciden
     */
    CompletableFuture<List<User>> searchUsersByName(String query);
}
