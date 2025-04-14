package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.shared.models.Invoice

@Composable
fun InvoiceScreen(
    invoices: List<Invoice>,
    onAddInvoice: (Invoice) -> Unit
) {
    var invoiceNumber by remember { mutableStateOf("") }
    var invoiceDate by remember { mutableStateOf("") }
    var poNumber by remember { mutableStateOf("") }
    var jobDescription by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Invoices", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = invoiceNumber,
            onValueChange = { invoiceNumber = it },
            label = { Text("Invoice Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = invoiceDate,
            onValueChange = { invoiceDate = it },
            label = { Text("Invoice Date (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = poNumber,
            onValueChange = { poNumber = it },
            label = { Text("PO Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = jobDescription,
            onValueChange = { jobDescription = it },
            label = { Text("Job Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // For simplicity, we convert invoice number to Int, and leave financials as null.
            invoiceNumber.toIntOrNull()?.let { num ->
                onAddInvoice(
                    Invoice(
                    invoiceNumber = num,
                    invoiceDate = invoiceDate,
                    poNumber = poNumber.takeIf { it.isNotBlank() },
                    customerId = 0, // You could select a customer from a drop-down in a real app
                    jobDescription = jobDescription.takeIf { it.isNotBlank() },
                    invoiceSubtotal = null,
                    invoiceGstAmount = null,
                    invoiceTotal = null
                )
                )
                invoiceNumber = ""
                invoiceDate = ""
                poNumber = ""
                jobDescription = ""
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Add Invoice")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(invoices) { invoice ->
                Text("Invoice #${invoice.invoiceNumber} - ${invoice.invoiceDate}", style = MaterialTheme.typography.body1)
                Divider()
            }
        }
    }
}
