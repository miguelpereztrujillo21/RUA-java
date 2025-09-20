package com.mpt.rua_java;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mpt.rua_java.databinding.ActivityMainBinding;
import com.mpt.rua_java.domain.entity.User;
import com.mpt.rua_java.presentation.ui.adapter.UserAdapter;
import com.mpt.rua_java.presentation.util.QRCodeGenerator;
import com.mpt.rua_java.presentation.viewmodel.UserViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;
import android.widget.ImageView;

/**
 * Activity principal de la aplicación
 * Implementa todos los requisitos del proyecto:
 * - Muestra 100 usuarios de RandomUser.me
 * - Permite agregar a contactos
 * - Genera QR funcional para cada contacto
 * - Búsqueda de usuarios
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements UserAdapter.OnUserClickListener {

    private ActivityMainBinding binding;
    private UserViewModel viewModel;
    private UserAdapter adapter;

    @Inject
    QRCodeGenerator qrGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupViewModel();
        setupRecyclerView();
        setupObservers();
        setupListeners();

        // Cargar usuarios guardados al iniciar
        viewModel.loadLocalUsers();
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    private void setupRecyclerView() {
        adapter = new UserAdapter();
        adapter.setOnUserClickListener(this);
        binding.recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewUsers.setAdapter(adapter);
    }

    private void setupObservers() {
        // Observar lista de usuarios
        viewModel.users.observe(this, users -> {
            System.out.println("DEBUG UI: Lista actualizada con " + (users != null ? users.size() : 0) + " usuarios");
            if (users != null) {
                for (int i = 0; i < Math.min(3, users.size()); i++) {
                    System.out.println("DEBUG UI: Usuario " + i + " - " + users.get(i).getFullName());
                }
            }

            // Verificar si estamos en modo contactos y forzar recreación del adapter
            Boolean showingContacts = viewModel.showingContacts.getValue();
            if (showingContacts != null && showingContacts) {
                System.out.println("DEBUG UI: Modo contactos activo - recreando adapter");
                // Recrear completamente el adapter para modo contactos
                adapter = new UserAdapter();
                adapter.setOnUserClickListener(this);
                binding.recyclerViewUsers.setAdapter(adapter);
                // Scroll al inicio
                binding.recyclerViewUsers.scrollToPosition(0);
            }

            adapter.setUsers(users);

            // Mostrar mensaje si no hay usuarios
            if (users == null || users.isEmpty()) {
                showEmptyState();
            } else {
                hideEmptyState();
            }
        });

        // Observar estado de carga
        viewModel.loading.observe(this, isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            binding.buttonLoadUsers.setEnabled(!isLoading);
            binding.buttonViewContacts.setEnabled(!isLoading);
            binding.buttonRefresh.setEnabled(!isLoading);
        });

        // Observar si estamos en modo "ver contactos"
        viewModel.showingContacts.observe(this, showingContacts -> {
            if (showingContacts != null && showingContacts) {
                // Cambiar texto del botón refresh cuando estamos viendo contactos
                binding.buttonRefresh.setText("Ver Todos");
                // Limpiar búsqueda cuando entramos al modo contactos
                binding.editTextSearch.setText("");
            } else {
                // Restaurar texto original del botón refresh
                binding.buttonRefresh.setText(getString(R.string.refresh));
            }
        });

        // Observar errores
        viewModel.error.observe(this, error -> {
            if (error != null) {
                binding.textError.setText(error);
                binding.textError.setVisibility(View.VISIBLE);
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            } else {
                binding.textError.setVisibility(View.GONE);
            }
        });

        // Observar carga exitosa
        viewModel.dataLoadedSuccessfully.observe(this, success -> {
            if (success != null && success) {
                Toast.makeText(this, "¡100 usuarios cargados exitosamente!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupListeners() {
        // Botón para cargar 100 usuarios de la API
        binding.buttonLoadUsers.setOnClickListener(v -> {
            viewModel.clearError();
            viewModel.loadRandomUsers();
        });

        // Botón para ver agenda de contactos
        binding.buttonViewContacts.setOnClickListener(v -> {
            viewModel.clearError();
            Toast.makeText(this, "Cargando agenda de contactos...", Toast.LENGTH_SHORT).show();
            viewModel.loadContacts();
        });

        // Botón para actualizar/refrescar - comportamiento dinámico
        binding.buttonRefresh.setOnClickListener(v -> {
            viewModel.clearError();
            Boolean showingContacts = viewModel.showingContacts.getValue();
            if (showingContacts != null && showingContacts) {
                // Si estamos viendo contactos, volver a mostrar todos los usuarios
                viewModel.showAllUsers();
            } else {
                // Si estamos viendo todos, refrescar la lista actual
                viewModel.loadLocalUsers();
            }
        });

        // Campo de búsqueda
        binding.editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String query = s.toString().trim();
                viewModel.searchUsers(query);
            }
        });
    }

    @Override
    public void onAddToContactsClick(User user) {
        // Implementa el requisito: "añadir a la agenda de contactos"
        new AlertDialog.Builder(this)
            .setTitle("Agregar a Contactos")
            .setMessage("¿Deseas agregar a " + user.getFullName() + " a tu agenda de contactos?")
            .setPositiveButton("Sí", (dialog, which) -> {
                viewModel.addToContacts(user.getId());
                Toast.makeText(this, user.getFullName() + " agregado a contactos", Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("Cancelar", null)
            .show();
    }

    @Override
    public void onUserClick(User user) {
        // Mostrar detalles completos del usuario
        showUserDetails(user);
    }

    @Override
    public void onQRClick(User user) {
        // Implementa el requisito: "QR funcional que permita guardar el contacto"
        generateAndShowQR(user);
    }

    private void showUserDetails(User user) {
        String details = "Nombre completo: " + user.getFullName() + "\n" +
                        "Email: " + user.getEmail() + "\n" +
                        "Teléfono: " + user.getPhone() + "\n" +
                        "Celular: " + user.getCell() + "\n" +
                        "Dirección: " + user.getFullAddress() + "\n" +
                        "Género: " + user.getGender() + "\n" +
                        "Nacionalidad: " + user.getNat() + "\n" +
                        "Edad: " + user.getDob().getAge() + " años\n" +
                        "Usuario: " + user.getLogin().getUsername();

        new AlertDialog.Builder(this)
            .setTitle("Detalles del Usuario")
            .setMessage(details)
            .setPositiveButton("Cerrar", null)
            .show();
    }

    private void generateAndShowQR(User user) {
        try {
            // Generar datos vCard para QR funcional
            String vCardData = user.getVCardData();
            Bitmap qrBitmap = qrGenerator.generateContactQR(vCardData);

            if (qrBitmap != null) {
                // Mostrar QR en dialog
                ImageView imageView = new ImageView(this);
                imageView.setImageBitmap(qrBitmap);
                imageView.setPadding(32, 32, 32, 32);

                new AlertDialog.Builder(this)
                    .setTitle("Código QR - " + user.getFullName())
                    .setMessage("Escanea este código para guardar el contacto")
                    .setView(imageView)
                    .setPositiveButton("Cerrar", null)
                    .show();
            } else {
                Toast.makeText(this, "Error al generar código QR", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void showEmptyState() {
        Boolean showingContacts = viewModel.showingContacts.getValue();
        if (showingContacts != null && showingContacts) {
            binding.textError.setText("No tienes contactos en tu agenda. Agrega algunos usuarios primero.");
        } else {
            binding.textError.setText("No hay usuarios. Presiona 'Cargar 100 Usuarios' para obtener datos de RandomUser.me");
        }
        binding.textError.setVisibility(View.VISIBLE);
    }

    private void hideEmptyState() {
        if (binding.textError.getText().toString().contains("No hay usuarios") ||
            binding.textError.getText().toString().contains("No tienes contactos")) {
            binding.textError.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}