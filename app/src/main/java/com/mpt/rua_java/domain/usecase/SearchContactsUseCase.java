package com.mpt.rua_java.domain.usecase;

import com.mpt.rua_java.domain.entity.User;
import com.mpt.rua_java.domain.repository.UserRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;

/**
 * Caso de uso para buscar únicamente dentro de la agenda (isAddedToContacts = true)
 */
public class SearchContactsUseCase {

    private final UserRepository userRepository;

    @Inject
    public SearchContactsUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CompletableFuture<List<User>> execute(String query) {
        return userRepository.searchContactsByName(query);
    }
}

