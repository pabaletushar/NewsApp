package com.tushar.newsapp.data.ui.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tushar.newsapp.data.model.ApiArticle
import com.tushar.newsapp.utils.AppConstant.COUNTRY
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
class TopHeadlineViewModel @Inject constructor(
    private val topHeadlineUseCases: TopHeadlineUseCases,
    ) : ViewModel() {
    private val _uiState = MutableStateFlow<FlowResponse<List<ApiArticle>>>(FlowResponse.loading())

    val uiState: StateFlow<FlowResponse<List<ApiArticle>>> = _uiState

    init {
        fetchNews(COUNTRY)
    }

    private fun fetchNews(country: String) {
        viewModelScope.launch {
            topHeadlineUseCases.invoke(country)
                .doOnSuccess {  _uiState.value = FlowResponse.success(it) }
                .doOnFailure { _uiState.value = FlowResponse.error(it!!) }
                .doOnLoading { _uiState.value =FlowResponse.loading() }
                .collect()
        }
    }
}