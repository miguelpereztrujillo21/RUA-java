package com.mpt.rua_java.di;

import com.mpt.rua_java.data.repository.UserRepositoryImpl;
import com.mpt.rua_java.domain.repository.UserRepository;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

/**
 * Módulo de inyección de dependencias para repositorios
 * Vincula las implementaciones con las interfaces del dominio
 */
@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {

    @Binds
    @Singleton
    public abstract UserRepository bindUserRepository(UserRepositoryImpl userRepositoryImpl);
}
