package ru.yandex.practicum.contacts.presentation.messengers

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.yandex.practicum.contacts.data.models.MessagingApp
import ru.yandex.practicum.contacts.presentation.ui.components.CommonBottomSheet

@Composable
fun MessengersBottomSheet(
    selectedApps: Set<MessagingApp>,
    onAppsSelected: (Set<MessagingApp>) -> Unit,
    onDismiss: () -> Unit
) {
    CommonBottomSheet(
        title = "Filter by Messaging App",
        items = MessagingApp.entries,
        selectedItems = selectedApps,
        onItemsSelected = onAppsSelected,
        onDismiss = onDismiss
    ) { app, isSelected ->
        MessengerOption(
            isSelected = isSelected,
            app = app,
            onAppSelected = { selectedApp ->
                val newSelection = if (selectedApp in selectedApps) {
                    selectedApps - selectedApp
                } else {
                    selectedApps + selectedApp
                }
                onAppsSelected(newSelection)
            }
        )
    }
}

@Composable
private fun MessengerOption(
    isSelected: Boolean,
    app: MessagingApp,
    onAppSelected: (MessagingApp) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onAppSelected(app) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(app.name)
    }
}