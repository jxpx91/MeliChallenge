package com.jp.mercadolibrechallenge.ui.views.detailsView

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jp.mercadolibrechallenge.R
import com.jp.mercadolibrechallenge.model.SearchResultResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView(
    navigateUp: () -> Unit,
) {
    val viewModel: DetailsViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.collectAsState()
    val details by viewModel.selectedItem.collectAsState()
    viewModel.getSelectedItem()
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.product)) },
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
                DetailsView(
                    modifier = Modifier.padding(padding),
                    selected = details,
                    openMeliPage = {
                        uriHandler.openUri(details.permalink)
                    })
            }
        })
}

@Composable
fun DetailsView(
    modifier: Modifier,
    selected: SearchResultResponse,
    openMeliPage: () -> Unit
) {
    Column (
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(240.dp),
                model = selected.thumbnail,
                contentDescription = null,
                placeholder = painterResource(R.drawable.meli_logo),
                error = painterResource(id = R.drawable.meli_logo),
            )
            Column {
                Text(
                    text = selected.condition,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.meli_blue)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = selected.title,
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$ ${selected.price}",
                style = MaterialTheme.typography.headlineLarge,
            )
            Text(
                modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
                text = selected.currency,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = stringResource(id = R.string.attributes_title),
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(16.dp))
        selected.attributes.forEach { attribute ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "${attribute.name}:",
                    style = MaterialTheme.typography.titleSmall,
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = attribute.value,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.meli_blue)),
            onClick = {
                openMeliPage()
            }) {
            Text(text = stringResource(id = R.string.see_on_meli))
        }
    }
}