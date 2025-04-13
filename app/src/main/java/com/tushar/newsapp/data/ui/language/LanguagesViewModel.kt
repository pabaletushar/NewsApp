package com.tushar.newsapp.data.ui.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tushar.newsapp.data.model.Language
import com.tushar.newsapp.utils.FlowResponse
import com.tushar.newsapp.utils.doOnFailure
import com.tushar.newsapp.utils.doOnLoading
import com.tushar.newsapp.utils.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguagesViewModel @Inject constructor(
    private val languageUseCases: LanguagesUseCases,
) : ViewModel() {
    private val _uiState = MutableStateFlow<FlowResponse<List<Language>>>(FlowResponse.loading())

    val uiState: StateFlow<FlowResponse<List<Language>>> = _uiState

    init {
        fetchLanguage()
    }

    fun fetchLanguage() {
        viewModelScope.launch {
            languageUseCases.invoke()
                .doOnSuccess {  _uiState.value = FlowResponse.success(it) }
                .doOnFailure { _uiState.value = FlowResponse.error(it!!) }
                .doOnLoading { _uiState.value = FlowResponse.loading() }
                .collect()
        }
    }

}