package org.example.project.shared.vm

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import org.example.project.shared.models.Customer
import org.example.project.shared.models.EquipmentCategory
import org.example.project.shared.models.ErrorLogEntry
import org.example.project.shared.models.Invoice
import org.example.project.shared.models.InvoiceLineItem
import org.example.project.shared.models.Manufacturer

class ManufacturerVM(
    private val repository: ManufacturerRepository = ManufacturerRepository()
) {
    // Expose state to the UI
    val manufacturers: SnapshotStateList<Manufacturer> = mutableStateListOf()
    val categories: SnapshotStateList<EquipmentCategory> = mutableStateListOf()
    val customers: SnapshotStateList<Customer> = mutableStateListOf()
    val invoices: SnapshotStateList<Invoice> = mutableStateListOf()
    val lineItems: SnapshotStateList<InvoiceLineItem> = mutableStateListOf()
    val errorLogs: SnapshotStateList<ErrorLogEntry> = mutableStateListOf()

    fun addManufacturer(manufacturer: Manufacturer) {
        manufacturers.add(manufacturer)
    }

    fun addCategory(category: EquipmentCategory) {
        categories.add(category)
    }

    fun addCustomer(customer: Customer) {
        customers.add(customer)
    }

    fun addInvoice(invoice: Invoice) {
        invoices.add(invoice)
    }

    fun addLineItem(lineItem: InvoiceLineItem) {
        lineItems.add(lineItem)
    }
}