package com.tushar.newsapp.data.ui.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tushar.newsapp.data.model.ApiSource
import com.tushar.newsapp.data.model.Country
import com.tushar.newsapp.utils.AppConstant
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
class CountryViewModel @Inject constructor(
    private val countryUseCases: CountryUseCases,
) : ViewModel() {
    private val _uiState = MutableStateFlow<FlowResponse<List<Country>>>(FlowResponse.loading())

    val uiState: StateFlow<FlowResponse<List<Country>>> = _uiState

    init {
        fetchNews(AppConstant.COUNTRY)
    }

    fun fetchNews(country: String) {
        viewModelScope.launch {
            countryUseCases.invoke()
                .doOnSuccess {  _uiState.value = FlowResponse.success(it) }
                .doOnFailure { _uiState.value = FlowResponse.error(it!!) }
                .doOnLoading { _uiState.value = FlowResponse.loading() }
                .collect()
        }
    }

}