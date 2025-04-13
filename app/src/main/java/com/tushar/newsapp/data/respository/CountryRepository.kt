package com.tushar.newsapp.data.respository


import com.tushar.newsapp.data.model.Country
import com.tushar.newsapp.utils.AppConstant
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@ViewModelScoped
class CountryRepository @Inject constructor(){

    fun getCountry(): List<Country>{
        return AppConstant.COUNTRIES
    }
}