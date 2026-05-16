package com.lab.lab03.model

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: String,
    val categoria: String,
    val favorito: Boolean = false
)

val productosDePrueba = listOf(
    Producto(1, "Cuaderno universitario A4", "S/ 12.00", "Utiles academicos", true),
    Producto(2, "Lapiceros pack x3", "S/ 6.50", "Utiles academicos"),
    Producto(3, "Folder institucional", "S/ 4.00", "Papeleria"),
    Producto(4, "Mochila universitaria", "S/ 85.00", "Accesorios", true),
    Producto(5, "Polo de la universidad", "S/ 35.00", "Merchandising"),
    Producto(6, "Tomatodo universitario", "S/ 22.00", "Accesorios"),
    Producto(7, "Calculadora cientifica", "S/ 65.00", "Tecnologia", true),
    Producto(8, "USB 32GB", "S/ 28.00", "Tecnologia"),
    Producto(9, "Libro de programacion Kotlin", "S/ 90.00", "Libros"),
    Producto(10, "Libro de matematica basica", "S/ 75.00", "Libros"),
    Producto(11, "Carnet universitario holder", "S/ 10.00", "Accesorios"),
    Producto(12, "Agenda academica 2026", "S/ 18.00", "Papeleria")
)