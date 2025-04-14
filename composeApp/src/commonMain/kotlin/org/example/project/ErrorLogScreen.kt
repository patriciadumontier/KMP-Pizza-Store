package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.shared.models.ErrorLogEntry

@Composable
fun ErrorLogScreen(errorLogs: List<ErrorLogEntry>) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Error Log", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(errorLogs) { logEntry ->
                Column(modifier = Modifier.padding(4.dp)) {
                    Text("Invoice: ${logEntry.invoiceNumber}", style = MaterialTheme.typography.subtitle1)
                    Text("Column: ${logEntry.columnName}")
                    Text("Offending Value: ${logEntry.offendingValue}")
                    Text("Error: ${logEntry.errorMessage}")
                    Text("Logged at: ${logEntry.loggedAt}", style = MaterialTheme.typography.caption)
                }
                Divider()
            }
        }
    }
}
