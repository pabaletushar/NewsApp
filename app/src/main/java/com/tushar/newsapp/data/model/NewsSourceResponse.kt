package com.tushar.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class NewsSourceResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("sources")
    val apiSources: List<ApiSource> = ArrayList(),
)
