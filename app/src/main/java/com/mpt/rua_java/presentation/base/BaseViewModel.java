package com.mpt.rua_java.presentation.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel<S> extends ViewModel {

    protected final MutableLiveData<UiState<S>> _state = new MutableLiveData<>(UiState.idle());
    public final LiveData<UiState<S>> state = _state;

    protected final MutableLiveData<Event<String>> _toast = new MutableLiveData<>();
    public final LiveData<Event<String>> toast = _toast;

    // Helpers comunes
    protected void setLoading() { _state.postValue(UiState.loading()); }
    protected void postSuccess(S data) { _state.postValue(UiState.success(data)); }
    protected void postError(String message) { _state.postValue(UiState.error(message)); }
    protected void postToast(String message) { _toast.postValue(new Event<>(message)); }
}

