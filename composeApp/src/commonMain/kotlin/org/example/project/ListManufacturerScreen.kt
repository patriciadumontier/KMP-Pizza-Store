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
import org.example.project.shared.models.Manufacturer
import org.example.project.shared.vm.ManufacturerVM

@Composable
fun ListManufacturerScreen(vm: ManufacturerVM) {
    var newManufacturer by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = newManufacturer,
            onValueChange = { newManufacturer = it },
            label = { Text("Add Manufacturer") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (newManufacturer.isNotBlank()) {
                    vm.addManufacturer(Manufacturer(name = newManufacturer))
                    newManufacturer = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Manufacturer")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Text("Existing Manufacturers", style = MaterialTheme.typography.h6, modifier = Modifier.padding(vertical = 8.dp))
        LazyColumn {
            items(vm.manufacturers) { manufacturer ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = 2.dp
                ) {
                    Text(
                        text = manufacturer.name,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}