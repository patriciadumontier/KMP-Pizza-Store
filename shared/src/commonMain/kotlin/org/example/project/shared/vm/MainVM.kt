package org.example.project.shared.vm

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.project.shared.models.Customer
import org.example.project.shared.models.Invoice
import org.example.project.shared.models.Manufacturer

class MainVM(
    private val manufacturerRepository: ManufacturerRepository = ManufacturerRepository(),
    private val customerRepository: CustomerRepository = CustomerRepository(),
    private val invoiceRepository: InvoiceRepository = InvoiceRepository()
) {
    // Observable state lists for Compose to monitor:
    val manufacturers: SnapshotStateList<Manufacturer> = mutableStateListOf()
    val customers: SnapshotStateList<Customer> = mutableStateListOf()
    val invoices: SnapshotStateList<Invoice> = mutableStateListOf()

    // Load initial data (you could also use init { ... })
    fun loadManufacturers() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = manufacturerRepository.fetchAllManufacturers()
            withContext(Dispatchers.Main) {
                manufacturers.clear()
                manufacturers.addAll(data)
            }
        }
    }

    fun loadCustomers() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = customerRepository.fetchAllCustomers()
            withContext(Dispatchers.Main) {
                customers.clear()
                customers.addAll(data)
            }
        }
    }

    fun loadInvoices() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = invoiceRepository.fetchAllInvoices()
            withContext(Dispatchers.Main) {
                invoices.clear()
                invoices.addAll(data)
            }
        }
    }

    // Functions for adding new data.
    fun addManufacturer(manufacturer: Manufacturer) {
        CoroutineScope(Dispatchers.IO).launch {
            // If you have a repository function to persist, call it here.
            withContext(Dispatchers.Main) {
                manufacturers.add(manufacturer)
            }
        }
    }

    fun addCustomer(customer: Customer) {
        CoroutineScope(Dispatchers.IO).launch {
            // Similarly, persist the data if needed
            withContext(Dispatchers.Main) {
                customers.add(customer)
            }
        }
    }

    fun addInvoice(invoice: Invoice) {
        CoroutineScope(Dispatchers.IO).launch {
            // Persist if needed
            withContext(Dispatchers.Main) {
                invoices.add(invoice)
            }
        }
    }
}