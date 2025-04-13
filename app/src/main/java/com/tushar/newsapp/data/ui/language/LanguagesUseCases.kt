package com.tushar.newsapp.data.ui.language

import com.tushar.newsapp.data.model.Country
import com.tushar.newsapp.data.model.Language
import com.tushar.newsapp.data.respository.CountryRepository
import com.tushar.newsapp.data.respository.LanguagesRepository
import com.tushar.newsapp.utils.FlowResult
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class LanguagesUseCases @Inject constructor(private val languageRepository: LanguagesRepository) {

    suspend fun invoke(): Flow<FlowResult<List<Language>>>{
        return flow {
            try {
                val countries =languageRepository.getLanguages()
                emit(FlowResult.Success(countries))
            } catch (e: Exception) {
                emit(FlowResult.Failure("Error", null))
            }
        }
    }
}