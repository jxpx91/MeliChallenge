package com.jp.mercadolibrechallenge.utils.extensions

import com.google.gson.Gson
import com.jp.mercadolibrechallenge.model.SearchResultResponse

fun String.toSearchResultResponse(): SearchResultResponse {
    if (this.isEmpty()) return SearchResultResponse.EMPTY
    return try {
        Gson().fromJson(this, SearchResultResponse::class.java)
    } catch (e: Exception) {
        SearchResultResponse.EMPTY
    }
}