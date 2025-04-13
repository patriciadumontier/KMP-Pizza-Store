package org.example.project.models

import kotlinx.serialization.Serializable

@Serializable
data class Invoice(
    val invoiceNumber: Int,
    val invoiceDate: String,  // You might use LocalDate in a real app
    val poNumber: String?,
    val customerId: Int,
    val jobDescription: String?,
    val invoiceSubtotal: Double?,
    val invoiceGstAmount: Double?,
    val invoiceTotal: Double?
)
