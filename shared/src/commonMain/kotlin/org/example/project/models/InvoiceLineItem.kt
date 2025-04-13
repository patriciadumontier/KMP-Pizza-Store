package org.example.project.models

import kotlinx.serialization.Serializable

@Serializable
data class InvoiceLineItem(
    val lineItemId: Int = 0,
    val invoiceNumber: Int,
    val manufacturerId: Int?,
    val equipmentCategoryId: Int?,
    val serialNumber: String?,
    val frame: String?,
    val phase: String?,
    val equipmentTypeId: Int?,
    val power: Double?,
    val powerUnit: String?,
    val voltage: String?,
    val rpm: Int?,
    val quantity: Int,
    val lineItemDescription: String?,
    val taxCode: String?,
    val unitPrice: Double?,
    val amount: Double?,
    val repairTypeId: Int?
)