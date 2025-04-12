package org.example.project.models

import kotlinx.serialization.Serializable

@Serializable
data class PizzaOrder(
    val customerName: String,
    val pizzaType: String,
    val quantity: Int,
    val address: String
)