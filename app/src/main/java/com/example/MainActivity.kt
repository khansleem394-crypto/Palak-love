package com.example

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.ui.loader.LoaderScreen
import com.example.ui.menu.ModMenuScreen
import com.example.ui.splash.SplashScreen
import com.example.ui.theme.CyberBlack
import com.example.ui.theme.MyApplicationTheme

enum class AppScreen {
    Splash,
    Loader,
    PremiumMenu
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Supports borderless edge-to-edge drawing
        enableEdgeToEdge()
        
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = CyberBlack
                ) {
                    var currentScreen by remember { mutableStateOf(AppScreen.Splash) }
                    
                    when (currentScreen) {
                        AppScreen.Splash -> {
                            SplashScreen(
                                onSplashFinished = {
                                    currentScreen = AppScreen.Loader
                                }
                            )
                        }
                        AppScreen.Loader -> {
                            LoaderScreen(
                                onUnlockSuccess = {
                                    currentScreen = AppScreen.PremiumMenu
                                }
                            )
                        }
                        AppScreen.PremiumMenu -> {
                            ModMenuScreen(
                                onLockApp = {
                                    currentScreen = AppScreen.Loader
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
