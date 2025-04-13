package com.tushar.newsapp.data.api

import com.tushar.newsapp.data.model.NewsSourceResponse
import com.tushar.newsapp.data.model.TopHeadlinesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top-headlines")
    suspend fun getTopHeadlines2(@Query("country") country: String): Response<TopHeadlinesResponse>

    @GET("top-headlines/sources")
    suspend fun getNewsSources(): Response<NewsSourceResponse>



}