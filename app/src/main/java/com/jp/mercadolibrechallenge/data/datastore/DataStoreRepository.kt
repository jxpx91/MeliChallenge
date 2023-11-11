package com.jp.mercadolibrechallenge.data.datastore

import com.jp.mercadolibrechallenge.model.SearchResultResponse

interface DataStoreRepository {
    suspend fun saveItemSelected(item: SearchResultResponse)
    suspend fun getItemSelected(): SearchResultResponse
}