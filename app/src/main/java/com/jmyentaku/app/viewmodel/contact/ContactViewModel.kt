package com.jmyentaku.app.viewmodel.contact

import androidx.lifecycle.ViewModel
import com.jmyentaku.app.viewmodel.contact.state.ContactUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContactViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ContactUiState())
    val uiState: StateFlow<ContactUiState> = _uiState.asStateFlow()

    fun clearState() {
        _uiState.value = ContactUiState()
    }
}