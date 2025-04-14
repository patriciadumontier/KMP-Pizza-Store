package org.example.project.shared.models

import kotlinx.serialization.Serializable

@Serializable
data class InvoiceLineItem(
    val lineItemId: Int = 0,
    val invoiceNumber: Int, // Required field, no default (or you can default to 0 if appropriate)
    val manufacturerId: Int? = null,
    val equipmentCategoryId: Int? = null,
    val serialNumber: String = "",  // Default to empty string if not provided
    val frame: String? = null,
    val phase: String? = null,
    val equipmentTypeId: Int? = null,
    val power: Double? = null,
    val powerUnit: String? = null,
    val voltage: String? = null,
    val rpm: Int? = null,
    val quantity: Int, // Required field
    val lineItemDescription: String? = null,
    val taxCode: String? = null,
    val unitPrice: Double? = null,
    val amount: Double? = null,
    val repairTypeId: Int? = null
)
