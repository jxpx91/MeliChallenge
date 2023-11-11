package com.jp.mercadolibrechallenge.service

import com.jp.mercadolibrechallenge.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MercadoLibreService {

    @GET("search")
    suspend fun search(
        @Query("q") query: String
    ): Response<SearchResponse>

}