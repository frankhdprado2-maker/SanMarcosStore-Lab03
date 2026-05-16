package com.lab.lab03.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lab.lab03.ui.screens.CarritoScreen
import com.lab.lab03.ui.screens.DetalleProductoScreen
import com.lab.lab03.ui.screens.PerfilScreen
import com.lab.lab03.ui.screens.TiendaScreen

sealed class Ruta(
    val ruta: String,
    val etiqueta: String,
    val icono: ImageVector
) {
    data object Tienda : Ruta("tienda", "Tienda", Icons.Filled.Store)
    data object Carrito : Ruta("carrito", "Carrito", Icons.Filled.ShoppingCart)
    data object Perfil : Ruta("perfil", "Mi Perfil", Icons.Filled.Person)
}

private val pestanas = listOf(
    Ruta.Tienda,
    Ruta.Carrito,
    Ruta.Perfil
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    modoOscuro: Boolean,
    onModoOscuroChange: (Boolean) -> Unit
) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination

    val tituloActual = when (currentDestination?.route) {
        Ruta.Tienda.ruta -> "SanMarcos Store"
        Ruta.Carrito.ruta -> "Carrito"
        Ruta.Perfil.ruta -> "Mi Perfil"
        "detalle/{productoId}" -> "Detalle del producto"
        else -> "SanMarcos Store"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(tituloActual) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ) {
                pestanas.forEach { pestana ->
                    val seleccionada = currentDestination
                        ?.hierarchy
                        ?.any { it.route == pestana.ruta } == true

                    NavigationBarItem(
                        selected = seleccionada,
                        onClick = {
                            navController.navigate(pestana.ruta) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                pestana.icono,
                                contentDescription = pestana.etiqueta
                            )
                        },
                        label = {
                            Text(pestana.etiqueta)
                        }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Ruta.Tienda.ruta,
            modifier = Modifier.padding(padding)
        ) {
            composable(Ruta.Tienda.ruta) {
                TiendaScreen(
                    onProductoClick = { productoId ->
                        navController.navigate("detalle/$productoId")
                    }
                )
            }

            composable(Ruta.Carrito.ruta) {
                CarritoScreen()
            }

            composable(Ruta.Perfil.ruta) {
                PerfilScreen(
                    modoOscuro = modoOscuro,
                    onModoOscuroChange = onModoOscuroChange
                )
            }

            composable(
                route = "detalle/{productoId}",
                arguments = listOf(
                    navArgument("productoId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val productoId = backStackEntry.arguments?.getInt("productoId") ?: 0

                DetalleProductoScreen(
                    productoId = productoId,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}