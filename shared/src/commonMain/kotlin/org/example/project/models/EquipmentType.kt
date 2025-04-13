package org.example.project.models

import kotlinx.serialization.Serializable

@Serializable
data class EquipmentType(
    val equipmentTypeId: Int = 0,
    val type: String
)
