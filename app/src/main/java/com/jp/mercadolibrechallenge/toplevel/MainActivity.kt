package com.jp.mercadolibrechallenge.toplevel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jp.mercadolibrechallenge.ui.theme.MercadoLibreChallengeTheme
import com.jp.mercadolibrechallenge.ui.views.MainScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MercadoLibreChallengeTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}