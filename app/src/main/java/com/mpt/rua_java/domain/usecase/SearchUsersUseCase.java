package com.mpt.rua_java.domain.usecase;

import com.mpt.rua_java.domain.entity.User;
import com.mpt.rua_java.domain.repository.UserRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;

/**
 * Caso de uso para buscar usuarios por nombre
 * Implementa funcionalidad de búsqueda en la aplicación
 */
public class SearchUsersUseCase {

    private final UserRepository userRepository;

    @Inject
    public SearchUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CompletableFuture<List<User>> execute(String query) {
        if (query == null || query.trim().isEmpty()) {
            return CompletableFuture.completedFuture(List.of());
        }
        return userRepository.searchUsersByName(query.trim());
    }
}
