package ru.autoevents.auto_events_client.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



/**
 * Горизонтальный навигационный компонент, отображающий список кнопок.
 *
 * Используется для переключения между экранами или выполнения навигационных действий.
 * Компонент является "тупым" (stateless) и не содержит логики навигации —
 * она должна передаваться через [NavItem.onClick].
 *
 * @param items Список элементов навигации, каждый из которых будет отображён как кнопка
 */
@Composable
fun NavigationContent(items: List<NavItem>) {
    Row(
        modifier = Modifier
            .height(40.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { item ->
            Button(onClick = item.onClick) {
                Text(item.title)
            }
        }
    }
}

@Composable
@WebPreview
fun NavigationContentPreview() {
    NavigationContent(
        items = listOf(
            NavItem("Home") {},
            NavItem("Profile") {},
            NavItem("Settings") {}
        )
    )
}
