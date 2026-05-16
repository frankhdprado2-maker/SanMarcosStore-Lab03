package com.lab.lab03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lab.lab03.ui.navigation.AppNavigation
import com.lab.lab03.ui.theme.Lab03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab03Theme(dynamicColor = false) {
                AppNavigation()
            }
        }
    }
}