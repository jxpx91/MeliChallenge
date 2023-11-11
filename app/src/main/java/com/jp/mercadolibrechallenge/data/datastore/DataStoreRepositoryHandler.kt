package com.jp.mercadolibrechallenge.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.jp.mercadolibrechallenge.model.SearchResultResponse
import com.jp.mercadolibrechallenge.utils.extensions.toSearchResultResponse
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val ITEM_SELECTED = stringPreferencesKey("ITEM_SELECTED")

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "meli_data_store"
)

class DataStoreRepositoryHandler @Inject constructor(
    private val context: Context
): DataStoreRepository {

    override suspend fun saveItemSelected(item: SearchResultResponse) {
        context.dataStore.edit { preferences ->
            preferences[ITEM_SELECTED] = item.toString()
        }
    }

    override suspend fun getItemSelected(): SearchResultResponse {
        return context.dataStore.data.first()[ITEM_SELECTED].orEmpty().toSearchResultResponse()
    }
}