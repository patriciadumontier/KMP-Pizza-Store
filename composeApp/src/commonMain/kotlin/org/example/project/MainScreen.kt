package org.example.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.shared.vm.ManufacturerVM

@Composable
fun MainScreen() {
    val viewModel = remember { ManufacturerVM() }
    var selectedScreen by remember { mutableStateOf(AppScreen.Manufacturers) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Business Dashboard") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            )
        },
        bottomBar = {
            BottomNavigation {
                AppScreen.values().forEach { screen ->
                    BottomNavigationItem(
                        selected = selectedScreen == screen,
                        onClick = { selectedScreen = screen },
                        label = { Text(screen.title) },
                        // For the demo, use a simple Box as icon placeholder. Replace with proper Icons.
                        icon = { Box(modifier = Modifier.size(24.dp)) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            when (selectedScreen) {
                AppScreen.Manufacturers -> ListManufacturerScreen(vm = viewModel)
                AppScreen.Customers -> ListCustomerScreen(vm = viewModel)
                AppScreen.Invoices -> ListInvoiceScreen(vm = viewModel)
            }
        }
    }
}