package com.mpt.rua_java.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.mpt.rua_java.domain.entity.User;
import com.mpt.rua_java.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Tests unitarios para GetRandomUsersUseCase
 * Verifica la lógica de negocio del caso de uso principal
 */
@ExtendWith(MockitoExtension.class)
public class GetRandomUsersUseCaseTest {

    @Mock
    private UserRepository userRepository;

    private GetRandomUsersUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new GetRandomUsersUseCase(userRepository);
    }

    @Test
    void execute_shouldReturnUsers_whenRepositoryReturnsData() {
        // Given
        List<User> expectedUsers = createMockUsers();
        when(userRepository.getRandomUsers(100, null, null, null))
            .thenReturn(CompletableFuture.completedFuture(expectedUsers));

        // When
        CompletableFuture<List<User>> result = useCase.execute();

        // Then
        assertNotNull(result);
        List<User> actualUsers = result.join();
        assertEquals(expectedUsers.size(), actualUsers.size());
        assertEquals(expectedUsers.get(0).getId(), actualUsers.get(0).getId());

        verify(userRepository).getRandomUsers(100, null, null, null);
    }

    @Test
    void execute_shouldLimitTo100Users_whenMoreThan100Requested() {
        // Given
        List<User> expectedUsers = createMockUsers();
        when(userRepository.getRandomUsers(100, "male", "US", "test"))
            .thenReturn(CompletableFuture.completedFuture(expectedUsers));

        // When - intentamos solicitar 150 usuarios pero debe limitarse a 100
        CompletableFuture<List<User>> result = useCase.execute(150, "male", "US", "test");

        // Then
        assertNotNull(result);
        verify(userRepository).getRandomUsers(100, "male", "US", "test");
    }

    @Test
    void execute_shouldHandleRepositoryFailure() {
        // Given
        RuntimeException expectedException = new RuntimeException("API Error");
        when(userRepository.getRandomUsers(anyInt(), any(), any(), any()))
            .thenReturn(CompletableFuture.failedFuture(expectedException));

        // When
        CompletableFuture<List<User>> result = useCase.execute();

        // Then
        assertTrue(result.isCompletedExceptionally());

        // Verificar que la excepción es la esperada
        assertThrows(RuntimeException.class, result::join);
    }

    @Test
    void execute_withCustomParameters_shouldPassParametersCorrectly() {
        // Given
        List<User> expectedUsers = createMockUsers();
        when(userRepository.getRandomUsers(50, "female", "CA", "seed123"))
            .thenReturn(CompletableFuture.completedFuture(expectedUsers));

        // When
        CompletableFuture<List<User>> result = useCase.execute(50, "female", "CA", "seed123");

        // Then
        assertNotNull(result);
        verify(userRepository).getRandomUsers(50, "female", "CA", "seed123");
    }

    private List<User> createMockUsers() {
        // Crear usuarios mock para testing
        User user1 = mock(User.class);
        when(user1.getId()).thenReturn("user1");
        when(user1.getFullName()).thenReturn("John Doe");

        User user2 = mock(User.class);
        when(user2.getId()).thenReturn("user2");
        when(user2.getFullName()).thenReturn("Jane Smith");

        return Arrays.asList(user1, user2);
    }
}
