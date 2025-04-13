package com.tushar.newsapp.data.ui.language

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tushar.newsapp.data.model.Language
import com.tushar.newsapp.data.ui.base.ShowError
import com.tushar.newsapp.data.ui.base.ShowLoading
import com.tushar.newsapp.utils.AppConstant
import com.tushar.newsapp.utils.FlowResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageRoute(
    viewModel: LanguagesViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold (topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = AppConstant.APP_NAME) })
    }, content = { padding->
        Column (modifier = Modifier.padding(padding)){
            LanguageScreen(uiState)
        }
    })
}


@Composable
fun LanguageScreen(uiState: FlowResponse<List<Language>>) {
    when(uiState.status){
        FlowResponse.Status.SUCCESS -> {
            uiState.data?.let {
                LanguageList(it)
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

@Composable
fun LanguageList(languages:List<Language>){
    LazyColumn {
        items(languages) { language ->
            Language(language)
        }
    }
}

@Composable
fun Language(language: Language) {
    Column ( modifier = Modifier.padding(10.dp,10.dp)
        .fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {}, shape = RectangleShape , modifier = Modifier.size(width = 340.dp, height = 40.dp)){
            Text(text = language.name)
        }
    }
}


