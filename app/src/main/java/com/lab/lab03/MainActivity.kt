package com.lab.lab03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.lab.lab03.ui.navigation.AppNavigation
import com.lab.lab03.ui.theme.Lab03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var modoOscuro by rememberSaveable {
                mutableStateOf(false)
            }

            Lab03Theme(
                darkTheme = modoOscuro,
                dynamicColor = false
            ) {
                AppNavigation(
                    modoOscuro = modoOscuro,
                    onModoOscuroChange = { nuevoValor ->
                        modoOscuro = nuevoValor
                    }
                )
            }
        }
    }
}