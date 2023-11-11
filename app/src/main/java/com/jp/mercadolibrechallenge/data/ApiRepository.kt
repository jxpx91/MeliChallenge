package com.jp.mercadolibrechallenge.data

import android.util.Log
import com.jp.mercadolibrechallenge.model.SearchResponse
import com.jp.mercadolibrechallenge.service.RetrofitInstance
import javax.inject.Inject

class ApiRepository @Inject constructor() {
    private val TAG = "API_REPOSITORY"
    private val apiService = RetrofitInstance.apiService

    suspend fun getDataBySearch(query: String): SearchResponse {
        val response = apiService.search(query)
        return if (response.isSuccessful) {
            response.body() ?: SearchResponse.EMPTY
        } else {
            Log.e(TAG, response.errorBody().toString())
            SearchResponse.EMPTY
        }
    }
}