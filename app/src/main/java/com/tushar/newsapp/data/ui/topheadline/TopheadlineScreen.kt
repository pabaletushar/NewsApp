package com.tushar.newsapp.data.ui.topheadline

import androidx.compose.runtime.Composable
import com.tushar.newsapp.data.model.ApiArticle
import com.tushar.newsapp.data.ui.base.ArticeleList
import com.tushar.newsapp.data.ui.base.ShowError
import com.tushar.newsapp.data.ui.base.ShowLoading


import com.tushar.newsapp.utils.FlowResponse



@Composable
fun TopHeadlineScreen(uiState: FlowResponse<List<ApiArticle>>, onNewsClick: (url: String) -> Unit){
    when(uiState.status){
        FlowResponse.Status.SUCCESS -> {
            uiState.data?.let {
                ArticeleList(apiArticles = it, onNewsClick = onNewsClick)
            }
        }
        FlowResponse.Status.ERROR -> {
            // Handle error state
            ShowError(uiState.error ?: "Unknown error")
        }

        FlowResponse.Status.LOADING -> {
            // Handle loading state
            ShowLoading()
        }
    }
}