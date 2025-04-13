package org.example.project.models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorLogEntry(
    val invoiceNumber: String,
    val columnName: String,
    val offendingValue: String,
    val errorMessage: String,
    val loggedAt: String
)