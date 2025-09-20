package com.mpt.rua_java.di;

import com.mpt.rua_java.domain.repository.UserRepository;
import com.mpt.rua_java.domain.usecase.*;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

/**
 * Módulo de inyección de dependencias para casos de uso
 * Proporciona instancias de todos los casos de uso del dominio
 */
@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule {

    @Provides
    @Singleton
    public GetRandomUsersUseCase provideGetRandomUsersUseCase(UserRepository userRepository) {
        return new GetRandomUsersUseCase(userRepository);
    }

    @Provides
    @Singleton
    public SaveUsersUseCase provideSaveUsersUseCase(UserRepository userRepository) {
        return new SaveUsersUseCase(userRepository);
    }

    @Provides
    @Singleton
    public GetAllUsersUseCase provideGetAllUsersUseCase(UserRepository userRepository) {
        return new GetAllUsersUseCase(userRepository);
    }

    @Provides
    @Singleton
    public AddUserToContactsUseCase provideAddUserToContactsUseCase(UserRepository userRepository) {
        return new AddUserToContactsUseCase(userRepository);
    }

    @Provides
    @Singleton
    public GetContactsUseCase provideGetContactsUseCase(UserRepository userRepository) {
        return new GetContactsUseCase(userRepository);
    }

    @Provides
    @Singleton
    public SearchUsersUseCase provideSearchUsersUseCase(UserRepository userRepository) {
        return new SearchUsersUseCase(userRepository);
    }

    @Provides
    @Singleton
    public SearchContactsUseCase provideSearchContactsUseCase(UserRepository userRepository) {
        return new SearchContactsUseCase(userRepository);
    }
}
