package com.mpt.rua_java.domain.usecase;

import com.mpt.rua_java.domain.entity.User;
import com.mpt.rua_java.domain.repository.UserRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;

/**
 * Caso de uso para obtener usuarios aleatorios de la API RandomUser
 * Implementa la lógica de negocio para el consumo de la API
 * Sigue los principios SOLID - Single Responsibility
 */
public class GetRandomUsersUseCase {

    private final UserRepository userRepository;

    @Inject
    public GetRandomUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Ejecuta el caso de uso para obtener 100 usuarios aleatorios por defecto
     * @return CompletableFuture con lista de usuarios
     */
    public CompletableFuture<List<User>> execute() {
        return execute(100, null, null, null);
    }

    /**
     * Ejecuta el caso de uso con parámetros personalizados
     * @param results número de usuarios (máximo 100 según requisitos)
     * @param gender filtro por género
     * @param nationality filtro por nacionalidad
     * @param seed semilla para reproducir resultados
     * @return CompletableFuture con lista de usuarios
     */
    public CompletableFuture<List<User>> execute(int results, String gender, String nationality, String seed) {
        // Validar que no se excedan los 100 usuarios según requisitos
        if (results > 100) {
            results = 100;
        }
        return userRepository.getRandomUsers(results, gender, nationality, seed);
    }
}
