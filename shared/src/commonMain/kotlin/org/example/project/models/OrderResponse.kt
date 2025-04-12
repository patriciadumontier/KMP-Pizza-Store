package org.example.project.models

import kotlinx.serialization.Serializable

@Serializable
data class OrderResponse(
    val message: String
)