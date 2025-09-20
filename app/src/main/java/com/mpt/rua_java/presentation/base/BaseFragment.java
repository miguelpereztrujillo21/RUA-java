package com.mpt.rua_java.presentation.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

/**
 * Fragment base que automatiza la observación de estados (UiState) y eventos (Event) del ViewModel.
 * Usa viewLifecycleOwner para evitar fugas.
 *
 * @param <VM> Tipo de ViewModel que extiende BaseViewModel<S>
 * @param <S>  Tipo del dato que encapsula el UiState
 */
public abstract class BaseFragment<VM extends BaseViewModel<S>, S> extends Fragment {

    protected VM viewModel;

    /**
     * Provee la instancia del ViewModel (p.ej. con new ViewModelProvider(this).get(...)).
     */
    protected abstract VM provideViewModel();

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = provideViewModel();
        observeBase(viewModel, getViewLifecycleOwner());
    }

    protected void observeBase(VM vm, LifecycleOwner owner) {
        if (vm == null || owner == null) return;
        vm.state.observe(owner, this::handleState);
        vm.toast.observe(owner, event -> {
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

    // Hooks para que los Fragments hijos reaccionen a los estados
    protected void onLoading() { /* opcional */ }
    protected void onSuccess(@Nullable S data) { /* opcional */ }
    protected void onError(@Nullable String message) { if (message != null && !message.isEmpty()) showToast(message); }
    protected void onIdle() { /* opcional */ }

    protected void showToast(String msg) {
        if (getContext() != null) Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}

