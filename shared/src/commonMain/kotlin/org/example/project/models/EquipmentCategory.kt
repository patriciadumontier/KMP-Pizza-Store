package org.example.project.models

import kotlinx.serialization.Serializable

@Serializable
data class EquipmentCategory(
    val equipmentCategoryId: Int = 0,
    val code: String
)
