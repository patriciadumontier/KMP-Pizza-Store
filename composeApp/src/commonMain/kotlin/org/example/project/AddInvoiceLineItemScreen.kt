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
import org.example.project.shared.models.InvoiceLineItem

@Composable
fun InvoiceLineItemScreen(
    lineItems: List<InvoiceLineItem>,
    onAddLineItem: (InvoiceLineItem) -> Unit
) {
    var serialNumber by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    // Additional fields could be added similarly.

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Invoice Line Items", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = serialNumber,
            onValueChange = { serialNumber = it },
            label = { Text("Serial Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantity") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val qty = quantity.toIntOrNull() ?: 0
            onAddLineItem(
                InvoiceLineItem(
                invoiceNumber = 0,  // Should be set appropriately in a full app
                serialNumber = serialNumber,
                quantity = qty
            )
            )
            serialNumber = ""
            quantity = ""
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Add Line Item")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(lineItems) { item ->
                Text("Serial: ${item.serialNumber} - Qty: ${item.quantity}")
                Divider()
            }
        }
    }
}
