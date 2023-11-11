package com.jp.mercadolibrechallenge.ui.views.resultListView

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jp.mercadolibrechallenge.R
import com.jp.mercadolibrechallenge.model.SearchResultResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultListView(
    query: String,
    goToDetails: () -> Unit,
    navigateUp: () -> Unit,
) {
    val viewModel: ResultListViewModel = hiltViewModel()
    val data by viewModel.dataResponse.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    viewModel.searchByQuery(query)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = data.query) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onSecondary
                ),
                navigationIcon = {
                    IconButton(onClick = { navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = { padding ->
            AnimatedVisibility(visible = isLoading.not()) {
                LazyColumn(modifier = Modifier.padding(padding)) {
                    items(data.results) { dataRow ->
                        ItemRow(
                            dataRow = dataRow,
                            onSelect = { selected ->
                                viewModel.saveSelectedItem(selected)
                                goToDetails()
                            }
                        )
                    }
                }
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ItemRow(
    dataRow: SearchResultResponse,
    onSelect: (result: SearchResultResponse) -> Unit
) {
    ElevatedCard (
        modifier = Modifier.padding(16.dp),
        onClick = { onSelect(dataRow) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .height(80.dp),
                model = dataRow.thumbnail,
                contentDescription = null,
                placeholder = painterResource(R.drawable.meli_logo),
                error = painterResource(id = R.drawable.meli_logo),
            )
            Column {
                Text(
                    text = dataRow.title,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(text = "$ ${dataRow.price} ${dataRow.currency}")
            }
        }
    }
}