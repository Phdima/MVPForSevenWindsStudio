package com.example.mvpforsevenwindsstudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.mvpforsevenwindsstudio.core.ui.components.TopBar
import com.example.mvpforsevenwindsstudio.core.ui.theme.MVPforSevenWindsStudioTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVPforSevenWindsStudioTheme {
                TopBar()
            }
        }
    }
}

