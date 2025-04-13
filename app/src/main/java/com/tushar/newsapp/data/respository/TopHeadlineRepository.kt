package com.tushar.newsapp.data.respository

import com.tushar.newsapp.data.api.NetworkService
import com.tushar.newsapp.data.model.ApiArticle
import com.tushar.newsapp.data.model.TopHeadlinesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository @Inject constructor(private val networkService: NetworkService) {

    suspend fun getTopHeadlines2(
        country: String
    ): Response<TopHeadlinesResponse> {
        return networkService.getTopHeadlines2(country)
    }
}