package org.example.project.shared.models

import kotlinx.serialization.Serializable

@Serializable
data class OrderResponse(
    val message: String
)