package com.mpt.rua_java.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mpt.rua_java.domain.entity.User;
import com.mpt.rua_java.domain.usecase.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * ViewModel principal para la gestión de usuarios
 * Maneja el estado de la UI y coordina los casos de uso del dominio
 * No contiene lógica de negocio, solo orquesta los casos de uso
 */
@HiltViewModel
public class UserViewModel extends ViewModel {

    private final GetRandomUsersUseCase getRandomUsersUseCase;
    private final SaveUsersUseCase saveUsersUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final AddUserToContactsUseCase addUserToContactsUseCase;
    private final GetContactsUseCase getContactsUseCase;
    private final SearchUsersUseCase searchUsersUseCase;

    // LiveData para observar cambios en la UI
    private final MutableLiveData<List<User>> _users = new MutableLiveData<>();
    public LiveData<List<User>> users = _users;

    private final MutableLiveData<Boolean> _loading = new MutableLiveData<>();
    public LiveData<Boolean> loading = _loading;

    private final MutableLiveData<String> _error = new MutableLiveData<>();
    public LiveData<String> error = _error;

    private final MutableLiveData<Boolean> _dataLoadedSuccessfully = new MutableLiveData<>();
    public LiveData<Boolean> dataLoadedSuccessfully = _dataLoadedSuccessfully;

    // LiveData específico para mostrar si estamos en modo "ver contactos"
    private final MutableLiveData<Boolean> _showingContacts = new MutableLiveData<>(false);
    public LiveData<Boolean> showingContacts = _showingContacts;

    @Inject
    public UserViewModel(
        GetRandomUsersUseCase getRandomUsersUseCase,
        SaveUsersUseCase saveUsersUseCase,
        GetAllUsersUseCase getAllUsersUseCase,
        AddUserToContactsUseCase addUserToContactsUseCase,
        GetContactsUseCase getContactsUseCase,
        SearchUsersUseCase searchUsersUseCase
    ) {
        this.getRandomUsersUseCase = getRandomUsersUseCase;
        this.saveUsersUseCase = saveUsersUseCase;
        this.getAllUsersUseCase = getAllUsersUseCase;
        this.addUserToContactsUseCase = addUserToContactsUseCase;
        this.getContactsUseCase = getContactsUseCase;
        this.searchUsersUseCase = searchUsersUseCase;
    }

    /**
     * Carga 100 usuarios de la API RandomUser y los guarda localmente
     * Implementa el requisito: "desarrolla una manera de almacenar 100 resultados"
     */
    public void loadRandomUsers() {
        _loading.setValue(true);
        _error.setValue(null);

        getRandomUsersUseCase.execute()
            .thenCompose(users -> {
                // Guardar usuarios en base de datos local
                return saveUsersUseCase.execute(users)
                    .thenApply(v -> users); // Retornar los usuarios después de guardar
            })
            .thenAccept(users -> {
                _users.postValue(users);
                _loading.postValue(false);
                _dataLoadedSuccessfully.postValue(true);
            })
            .exceptionally(throwable -> {
                _error.postValue("Error al cargar usuarios: " + throwable.getMessage());
                _loading.postValue(false);
                _dataLoadedSuccessfully.postValue(false);
                return null;
            });
    }

    /**
     * Carga usuarios almacenados localmente
     */
    public void loadLocalUsers() {
        _loading.setValue(true);

        getAllUsersUseCase.execute()
            .thenAccept(users -> {
                _users.postValue(users);
                _loading.postValue(false);
            })
            .exceptionally(throwable -> {
                _error.postValue("Error al cargar usuarios locales: " + throwable.getMessage());
                _loading.postValue(false);
                return null;
            });
    }

    /**
     * Agrega un usuario a la agenda de contactos
     * Implementa el requisito: "añadir a la agenda de contactos"
     */
    public void addToContacts(String userId) {
        System.out.println("DEBUG: Agregando usuario a contactos: " + userId);
        addUserToContactsUseCase.execute(userId)
            .thenRun(() -> {
                System.out.println("DEBUG: Usuario agregado exitosamente, recargando lista");
                // Recargar la lista para mostrar el cambio
                if (_showingContacts.getValue() != null && _showingContacts.getValue()) {
                    loadContacts(); // Si estamos viendo contactos, recargar contactos
                } else {
                    loadLocalUsers(); // Si no, recargar todos los usuarios
                }
            })
            .exceptionally(throwable -> {
                System.out.println("DEBUG: Error al agregar a contactos: " + throwable.getMessage());
                _error.postValue("Error al agregar a contactos: " + throwable.getMessage());
                return null;
            });
    }

    /**
     * Consulta y muestra solo los usuarios que están en la agenda de contactos
     * Nueva funcionalidad para consultar la agenda
     */
    public void loadContacts() {
        _loading.postValue(true);
        _showingContacts.postValue(true);

        getContactsUseCase.execute()
            .thenAccept(contacts -> {
                // Agregar logs para depuración
                System.out.println("DEBUG: Contactos obtenidos: " + contacts.size());
                for (User contact : contacts) {
                    System.out.println("DEBUG: Contacto - " + contact.getFullName() + " - isAddedToContacts: " + contact.isAddedToContacts());
                }

                // Asegurar que solo se muestren los contactos
                if (contacts.isEmpty()) {
                    _users.postValue(new ArrayList<>());
                } else {
                    _users.postValue(new ArrayList<>(contacts)); // Crear nueva lista para forzar actualización
                }
                _loading.postValue(false);
            })
            .exceptionally(throwable -> {
                System.out.println("DEBUG: Error al cargar contactos: " + throwable.getMessage());
                throwable.printStackTrace();
                _error.postValue("Error al cargar contactos: " + throwable.getMessage());
                _loading.postValue(false);
                return null;
            });
    }

    /**
     * Vuelve a mostrar todos los usuarios (salir del modo contactos)
     */
    public void showAllUsers() {
        _showingContacts.postValue(false);
        loadLocalUsers();
    }

    /**
     * Busca usuarios por nombre
     */
    public void searchUsers(String query) {
        if (query == null || query.trim().isEmpty()) {
            loadLocalUsers();
            return;
        }

        _loading.setValue(true);

        searchUsersUseCase.execute(query)
            .thenAccept(users -> {
                _users.postValue(users);
                _loading.postValue(false);
            })
            .exceptionally(throwable -> {
                _error.postValue("Error en la búsqueda: " + throwable.getMessage());
                _loading.postValue(false);
                return null;
            });
    }

    /**
     * Limpia el estado de error
     */
    public void clearError() {
        _error.setValue(null);
    }
}
