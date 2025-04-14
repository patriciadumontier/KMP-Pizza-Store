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
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.shared.models.Invoice
import org.example.project.shared.vm.ManufacturerVM

@Composable
fun ListInvoiceScreen(vm: ManufacturerVM) {
    var invoiceNumber by remember { mutableStateOf("") }
    var invoiceDate by remember { mutableStateOf("") }
    var poNumber by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = invoiceNumber,
            onValueChange = { invoiceNumber = it },
            label = { Text("Invoice Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = invoiceDate,
            onValueChange = { invoiceDate = it },
            label = { Text("Invoice Date (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = poNumber,
            onValueChange = { poNumber = it },
            label = { Text("PO Number (optional)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                invoiceNumber.toIntOrNull()?.let { num ->
                    vm.addInvoice(
                        Invoice(
                            invoiceNumber = num,
                            invoiceDate = invoiceDate,
                            poNumber = poNumber.takeIf { it.isNotBlank() },
                            customerId = 0 // For demonstration; in a real app, you would select a customer.
                        )
                    )
                    invoiceNumber = ""
                    invoiceDate = ""
                    poNumber = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Invoice")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Text("Existing Invoices", style = MaterialTheme.typography.h6, modifier = Modifier.padding(vertical = 8.dp))
        LazyColumn {
            items(vm.invoices) { invoice ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = 2.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Invoice #${invoice.invoiceNumber}", style = MaterialTheme.typography.h6)
                        Text("Date: ${invoice.invoiceDate}", style = MaterialTheme.typography.body2)
                        invoice.poNumber?.let {
                            Text("PO: $it", style = MaterialTheme.typography.body2)
                        }
                    }
                }
            }
        }
    }
}
