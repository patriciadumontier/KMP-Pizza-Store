package org.example.project.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val customerId: Int = 0,
    val customerName: String,
    val address: String,
    val phone: String
)
