package org.example.project.shared.models

import kotlinx.serialization.Serializable

@Serializable
data class EquipmentCategory(
    val equipmentCategoryId: Int = 0,
    val code: String
)
