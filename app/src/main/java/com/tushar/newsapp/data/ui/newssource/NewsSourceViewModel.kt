package com.tushar.newsapp.data.ui.newssource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tushar.newsapp.data.model.ApiArticle
import com.tushar.newsapp.data.model.ApiSource
import com.tushar.newsapp.data.respository.NewsSourceRepository
import com.tushar.newsapp.data.ui.topheadline.TopHeadlineUseCases
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
class NewsSourceViewModel @Inject constructor(
    private val newsSourceUseCases: NewsSourceUseCases,
) : ViewModel() {
    private val _uiState = MutableStateFlow<FlowResponse<List<ApiSource>>>(FlowResponse.loading())

    val uiState: StateFlow<FlowResponse<List<ApiSource>>> = _uiState

    init {
        fetchNews(AppConstant.COUNTRY)
    }

    fun fetchNews(country: String) {
        viewModelScope.launch {
            newsSourceUseCases.invoke()
                .doOnSuccess {  _uiState.value = FlowResponse.success(it) }
                .doOnFailure { _uiState.value = FlowResponse.error(it!!) }
                .doOnLoading { _uiState.value = FlowResponse.loading() }
                .collect()
        }
    }
}