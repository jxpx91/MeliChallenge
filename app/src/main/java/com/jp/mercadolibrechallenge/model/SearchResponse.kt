package com.jp.mercadolibrechallenge.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val query: String,
    val results: List<SearchResultResponse>,
) {
    companion object {
        val EMPTY = SearchResponse(
            "",
            listOf()
        )
    }
}

data class SearchResultResponse(
    val id: String,
    val title: String,
    val condition: String,
    val permalink: String,
    val thumbnail: String,
    @SerializedName("currency_id")
    val currency: String,
    val price: Double,
    val attributes: List<SearchResultAttributesResponse>
) {

    companion object {
        val EMPTY = SearchResultResponse(
            "",
            "",
            "",
            "",
            "",
            "",
            0.0,
            listOf()
        )
    }
    override fun toString(): String {
        return Gson().toJson(this)
    }
}

data class SearchResultAttributesResponse(
    val name: String,
    @SerializedName("value_name")
    val value: String
)
