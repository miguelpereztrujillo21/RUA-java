package com.mpt.rua_java.domain.usecase;

import com.mpt.rua_java.domain.entity.User;
import com.mpt.rua_java.domain.repository.UserRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;

/**
 * Caso de uso para obtener todos los usuarios que están en la agenda de contactos
 * Implementa la funcionalidad de consultar la agenda de contactos
 */
public class GetContactsUseCase {

    private final UserRepository userRepository;

    @Inject
    public GetContactsUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Obtiene todos los usuarios que han sido agregados a la agenda de contactos
     * @return CompletableFuture con la lista de usuarios que están en contactos
     */
    public CompletableFuture<List<User>> execute() {
        return userRepository.getContactUsers();
    }
}
