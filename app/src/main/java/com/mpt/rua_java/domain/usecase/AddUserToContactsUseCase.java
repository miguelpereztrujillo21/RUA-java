package com.mpt.rua_java.domain.usecase;

import com.mpt.rua_java.domain.repository.UserRepository;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;

/**
 * Caso de uso para agregar un usuario a la agenda de contactos
 * Implementa el requisito de "añadir a la agenda de contactos"
 */
public class AddUserToContactsUseCase {

    private final UserRepository userRepository;

    @Inject
    public AddUserToContactsUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CompletableFuture<Void> execute(String userId) {
        return userRepository.updateUserContactStatus(userId, true);
    }
}
