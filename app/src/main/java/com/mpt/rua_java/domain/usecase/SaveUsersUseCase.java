package com.mpt.rua_java.domain.usecase;

import com.mpt.rua_java.domain.entity.User;
import com.mpt.rua_java.domain.repository.UserRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;

/**
 * Caso de uso para guardar usuarios en la base de datos local
 */
public class SaveUsersUseCase {

    private final UserRepository userRepository;

    @Inject
    public SaveUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CompletableFuture<Void> execute(List<User> users) {
        return userRepository.saveUsers(users);
    }
}
