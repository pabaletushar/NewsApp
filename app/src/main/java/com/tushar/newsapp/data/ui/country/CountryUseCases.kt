package com.tushar.newsapp.data.ui.country

import com.tushar.newsapp.data.model.Country
import com.tushar.newsapp.data.respository.CountryRepository
import com.tushar.newsapp.utils.FlowResult
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class CountryUseCases @Inject constructor(private val countryRepository: CountryRepository) {

    suspend fun invoke(): Flow<FlowResult<List<Country>>>{
        return flow {
            try {
                val countries =countryRepository.getCountry()
                emit(FlowResult.Success(countries))
            } catch (e: Exception) {
                emit(FlowResult.Failure("Error", null))
            }
        }
    }
}