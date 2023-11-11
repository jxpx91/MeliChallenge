package com.jp.mercadolibrechallenge.ui.views.detailsView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jp.mercadolibrechallenge.data.datastore.DataStoreRepository
import com.jp.mercadolibrechallenge.model.SearchResultResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
): ViewModel() {

    val isLoading = MutableStateFlow(true)
    val selectedItem = MutableStateFlow(SearchResultResponse.EMPTY)

    fun getSelectedItem() {
        viewModelScope.launch {
            selectedItem.emit(dataStoreRepository.getItemSelected())
            isLoading.emit(false)
        }
    }
}