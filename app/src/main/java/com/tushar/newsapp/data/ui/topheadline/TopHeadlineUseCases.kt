package com.tushar.newsapp.data.ui.topheadline

import com.tushar.newsapp.data.model.ApiArticle
import com.tushar.newsapp.data.respository.TopHeadlineRepository
import com.tushar.newsapp.utils.FlowResult
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.HttpURLConnection
import javax.inject.Inject


@ViewModelScoped
class TopHeadlineUseCases @Inject constructor(private val topHeadlineRepository: TopHeadlineRepository) {

    suspend fun invoke(country: String): Flow<FlowResult<List<ApiArticle>>> = flow {
        emit(FlowResult.Loading)
        val response = topHeadlineRepository.getTopHeadlines2(country)
        when {
            response.isSuccessful -> {
                val data = response.body()
                if (data!=null ){
                    if(data.status == "ok"){
                        emit(FlowResult.Success(data.apiArticles))
                    } else {
                        emit(FlowResult.Failure(data.status, null))
                    }
                } else {
                    val error = response.errorBody()
                    if (error!=null){
                        emit(FlowResult.Failure(response.errorBody().toString(),response.code()))
                    } else {
                        emit(FlowResult.Failure(response.errorBody().toString(),response.code()))
                    }
                }
            }
            response.code() in listOf(
                HttpURLConnection.HTTP_BAD_REQUEST,
                HttpURLConnection.HTTP_UNAUTHORIZED,
                HttpURLConnection.HTTP_NOT_FOUND
            ) -> emit(FlowResult.Failure(response.message(), response.code()))
            else -> emit(FlowResult.Failure("Unknown Error", response.code()))
        }
    }.catch {
        emit(FlowResult.Failure(it.toString(),null))
    }.flowOn(Dispatchers.IO)
}