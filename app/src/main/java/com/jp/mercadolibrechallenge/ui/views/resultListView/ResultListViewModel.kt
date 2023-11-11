package com.jp.mercadolibrechallenge.ui.views.resultListView

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jp.mercadolibrechallenge.data.ApiRepository
import com.jp.mercadolibrechallenge.data.datastore.DataStoreRepository
import com.jp.mercadolibrechallenge.model.SearchResponse
import com.jp.mercadolibrechallenge.model.SearchResultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultListViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dataStoreRepository: DataStoreRepository,
): ViewModel() {

    val isLoading = MutableStateFlow(true)
    val dataResponse = MutableStateFlow(SearchResponse.EMPTY)

    fun searchByQuery(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepository.getDataBySearch(query)
                dataResponse.emit(response)
            } catch (e: Exception) {
                Log.e("Search by query", e.message.toString())
            }
            isLoading.emit(false)
        }
    }

    fun saveSelectedItem(item: SearchResultResponse) {
        viewModelScope.launch {
            dataStoreRepository.saveItemSelected(item)
        }
    }
}