package com.mpt.rua_java.presentation.base;

import androidx.annotation.Nullable;

/**
 * Representa el estado de la UI de forma genérica.
 */
public class UiState<T> {
    public enum Status { IDLE, LOADING, SUCCESS, ERROR }

    public final Status status;
    @Nullable public final T data;
    @Nullable public final String message;

    private UiState(Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> UiState<T> idle() { return new UiState<>(Status.IDLE, null, null); }
    public static <T> UiState<T> loading() { return new UiState<>(Status.LOADING, null, null); }
    public static <T> UiState<T> success(@Nullable T data) { return new UiState<>(Status.SUCCESS, data, null); }
    public static <T> UiState<T> error(String message) { return new UiState<>(Status.ERROR, null, message); }
}

