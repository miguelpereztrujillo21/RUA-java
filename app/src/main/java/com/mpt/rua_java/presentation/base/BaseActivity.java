package com.mpt.rua_java.presentation.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity base que automatiza la observación de estados (UiState) y eventos (Event) del ViewModel.
 *
 * @param <VM> Tipo de ViewModel que extiende BaseViewModel<S>
 * @param <S>  Tipo del dato que encapsula el UiState
 */
public abstract class BaseActivity<VM extends BaseViewModel<S>, S> extends AppCompatActivity {

    protected VM viewModel;

    /**
     * Provee la instancia del ViewModel (p.ej. con new ViewModelProvider(this).get(...)).
     */
    protected abstract VM provideViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = provideViewModel();
        observeBase(viewModel);
    }

    protected void observeBase(VM vm) {
        if (vm == null) return;
        vm.state.observe(this, this::handleState);
        vm.toast.observe(this, event -> {
            String msg = event != null ? event.getContentIfNotHandled() : null;
            if (msg != null) showToast(msg);
        });
    }

    private void handleState(UiState<S> state) {
        if (state == null) return;
        switch (state.status) {
            case LOADING:
                onLoading();
                break;
            case SUCCESS:
                onSuccess(state.data);
                break;
            case ERROR:
                onError(state.message);
                break;
            case IDLE:
            default:
                onIdle();
                break;
        }
    }

    // Hooks para que las Activities hijas reaccionen a los estados
    protected void onLoading() { /* opcional */ }
    protected void onSuccess(@Nullable S data) { /* opcional */ }
    protected void onError(@Nullable String message) {
        if (message != null && !message.isEmpty()) showToast(message);
    }
    protected void onIdle() { /* opcional */ }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

