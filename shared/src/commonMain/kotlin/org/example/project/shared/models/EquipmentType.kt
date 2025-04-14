package org.example.project.shared.models

import kotlinx.serialization.Serializable

@Serializable
data class EquipmentType(
    val equipmentTypeId: Int = 0,
    val type: String
)
