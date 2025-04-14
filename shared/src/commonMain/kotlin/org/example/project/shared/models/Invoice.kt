package org.example.project.shared.models

import kotlinx.serialization.Serializable

@Serializable
data class Invoice(
    val invoiceNumber: Int,
    val invoiceDate: String,  // You might use LocalDate in a real app
    val poNumber: String?,
    val customerId: Int,
    val jobDescription: String? = null,
    val invoiceSubtotal: Double? = null,
    val invoiceGstAmount: Double? = null,
    val invoiceTotal: Double? = null
)
