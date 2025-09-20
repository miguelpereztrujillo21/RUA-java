package com.mpt.rua_java.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.mpt.rua_java.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.concurrent.CompletableFuture;

/**
 * Tests unitarios para AddUserToContactsUseCase
 * Verifica la funcionalidad de agregar usuarios a contactos
 */
@ExtendWith(MockitoExtension.class)
public class AddUserToContactsUseCaseTest {

    @Mock
    private UserRepository userRepository;

    private AddUserToContactsUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new AddUserToContactsUseCase(userRepository);
    }

    @Test
    void execute_shouldCallRepository_withCorrectParameters() {
        // Given
        String userId = "test-user-id";
        when(userRepository.updateUserContactStatus(userId, true))
            .thenReturn(CompletableFuture.completedFuture(null));

        // When
        CompletableFuture<Void> result = useCase.execute(userId);

        // Then
        assertNotNull(result);
        assertDoesNotThrow(() -> result.join());
        verify(userRepository).updateUserContactStatus(userId, true);
    }

    @Test
    void execute_shouldHandleRepositoryFailure() {
        // Given
        String userId = "test-user-id";
        RuntimeException expectedException = new RuntimeException("Database Error");
        when(userRepository.updateUserContactStatus(userId, true))
            .thenReturn(CompletableFuture.failedFuture(expectedException));

        // When
        CompletableFuture<Void> result = useCase.execute(userId);

        // Then
        assertTrue(result.isCompletedExceptionally());
        assertThrows(RuntimeException.class, result::join);
    }
}
