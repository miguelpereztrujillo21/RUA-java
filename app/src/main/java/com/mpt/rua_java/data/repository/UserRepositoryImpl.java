package com.mpt.rua_java.data.repository;

import androidx.annotation.NonNull;

import com.mpt.rua_java.data.local.dao.UserDao;
import com.mpt.rua_java.data.mapper.UserMapper;
import com.mpt.rua_java.data.remote.api.RandomUserApiService;
import com.mpt.rua_java.data.remote.dto.RandomUserResponseDto;
import com.mpt.rua_java.domain.entity.User;
import com.mpt.rua_java.domain.repository.UserRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementación del repositorio de usuarios
 * Coordina entre la fuente de datos remota (API RandomUser) y local (Room)
 * Implementa el patrón Repository de Clean Architecture
 */
@Singleton
public class UserRepositoryImpl implements UserRepository {

    private final RandomUserApiService apiService;
    private final UserDao userDao;
    private final Executor executor;

    @Inject
    public UserRepositoryImpl(RandomUserApiService apiService, UserDao userDao) {
        this.apiService = apiService;
        this.userDao = userDao;
        this.executor = Executors.newFixedThreadPool(4);
    }

    @Override
    public CompletableFuture<List<User>> getRandomUsers(int results, String gender, String nat, String seed) {
        CompletableFuture<List<User>> future = new CompletableFuture<>();

        Call<RandomUserResponseDto> call = apiService.getUsers(results, gender, nat, seed);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<RandomUserResponseDto> call, @NonNull Response<RandomUserResponseDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        List<User> users = response.body().getResults().stream()
                                .map(UserMapper::toDomainEntity)
                                .collect(Collectors.toList());
                        future.complete(users);
                    } catch (Exception e) {
                        future.completeExceptionally(e);
                    }
                } else {
                    future.completeExceptionally(new Exception("API call failed: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<RandomUserResponseDto> call, Throwable t) {
                future.completeExceptionally(t);
            }
        });

        return future;
    }

    @Override
    public CompletableFuture<Void> saveUsers(List<User> users) {
        return CompletableFuture.runAsync(() -> {
            try {
                List<com.mpt.rua_java.data.local.entity.UserEntity> entities = users.stream()
                    .map(UserMapper::toRoomEntity)
                    .collect(Collectors.toList());
                userDao.insertUsers(entities);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<List<User>> getAllUsers() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return userDao.getAllUsers().stream()
                    .map(UserMapper::toDomainEntity)
                    .collect(Collectors.toList());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<User> getUserById(String id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                com.mpt.rua_java.data.local.entity.UserEntity entity = userDao.getUserById(id);
                return entity != null ? UserMapper.toDomainEntity(entity) : null;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<Void> updateUserContactStatus(String userId, boolean isAddedToContacts) {
        return CompletableFuture.runAsync(() -> {
            try {
                userDao.updateUserContactStatus(userId, isAddedToContacts);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<List<User>> getContactUsers() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return userDao.getContactUsers().stream()
                    .map(UserMapper::toDomainEntity)
                    .collect(Collectors.toList());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<List<User>> getUsersByGender(String gender) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return userDao.getUsersByGender(gender).stream()
                    .map(UserMapper::toDomainEntity)
                    .collect(Collectors.toList());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<List<User>> getUsersByNationality(String nationality) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return userDao.getUsersByNationality(nationality).stream()
                    .map(UserMapper::toDomainEntity)
                    .collect(Collectors.toList());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }

    @Override
    public CompletableFuture<List<User>> searchUsersByName(String query) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return userDao.searchUsersByName(query).stream()
                    .map(UserMapper::toDomainEntity)
                    .collect(Collectors.toList());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor);
    }
}
