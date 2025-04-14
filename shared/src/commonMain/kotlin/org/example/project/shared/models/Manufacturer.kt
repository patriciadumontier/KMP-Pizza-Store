package org.example.project.shared.models

import kotlinx.serialization.Serializable

@Serializable
data class Manufacturer(
    val id: Int = 0,
    val name: String
)
