package com.mpt.rua_java.presentation.base;

import androidx.annotation.Nullable;

/**
 * Wrapper para eventos de una sola consumición (toasts, navegación, etc.).
 */
public class Event<T> {
    private final T content;
    private boolean hasBeenHandled = false;

    public Event(T content) { this.content = content; }

    /**
     * Devuelve el contenido y marca el evento como manejado.
     */
    @Nullable
    public T getContentIfNotHandled() {
        if (hasBeenHandled) return null;
        hasBeenHandled = true;
        return content;
    }

    /**
     * Permite acceder al contenido incluso si ya fue manejado.
     */
    public T peek() { return content; }
}

