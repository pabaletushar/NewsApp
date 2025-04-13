package com.tushar.newsapp.data.respository

import com.tushar.newsapp.data.api.NetworkService
import com.tushar.newsapp.data.model.NewsSourceResponse
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Response
import javax.inject.Inject

@ViewModelScoped
class NewsSourceRepository@Inject constructor(private val networkService: NetworkService) {

    suspend fun getNewsSource() : Response<NewsSourceResponse> {
        return networkService.getNewsSources()
    }
}