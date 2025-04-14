package org.example.project.shared.models

import kotlinx.serialization.Serializable

@Serializable
data class RepairType(
    val repairTypeId: Int = 0,
    val type: String
)
