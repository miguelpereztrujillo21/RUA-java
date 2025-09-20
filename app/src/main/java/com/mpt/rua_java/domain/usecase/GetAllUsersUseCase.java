package com.mpt.rua_java.domain.usecase;

import com.mpt.rua_java.domain.entity.User;
import com.mpt.rua_java.domain.repository.UserRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;

/**
 * Caso de uso para obtener todos los usuarios almacenados localmente
 */
public class GetAllUsersUseCase {

    private final UserRepository userRepository;

    @Inject
    public GetAllUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CompletableFuture<List<User>> execute() {
        return userRepository.getAllUsers();
    }
}
