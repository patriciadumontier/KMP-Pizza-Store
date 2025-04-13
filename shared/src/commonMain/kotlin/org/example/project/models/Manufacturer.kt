package org.example.project.models

import kotlinx.serialization.Serializable

@Serializable
data class Manufacturer(
    val manufacturerId: Int = 0,
    val name: String
)
