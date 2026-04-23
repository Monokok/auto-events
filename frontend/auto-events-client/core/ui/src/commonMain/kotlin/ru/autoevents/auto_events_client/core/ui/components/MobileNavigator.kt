package ru.autoevents.auto_events_client.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import auto_events_client.core.ui.generated.resources.Res
import auto_events_client.core.ui.generated.resources.ic_calendar
import auto_events_client.core.ui.generated.resources.ic_chevron_right
import auto_events_client.core.ui.generated.resources.ic_profile
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import ru.autoevents.auto_events_client.core.ui.theme.*

//короткое имя для типа composable-функции. Вместо того чтобы везде писать:val icon: (@Composable (isSelected: Boolean) -> Unit)? = null
//typealias MobileNavigatorIcon = @Composable (isSelected: Boolean) -> Unit

data class MobileNavigatorItem(
    val title: String,
    val selected: Boolean = false,
    val onClick: () -> Unit,
    val contentDescription: String = title,
    val icon: (@Composable (isSelected: Boolean) -> Unit)? = null, //    val icon: MobileNavigatorIcon? = null,
)

private val DefaultMobileNavigatorIcon = Res.drawable.ic_calendar

@Composable
fun MobileNavigator(
    items: List<MobileNavigatorItem>,
    modifier: Modifier = Modifier,
) {
    if (items.isEmpty()) return

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.white900,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        shadowElevation = 12.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 72.dp)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEach { item ->
                MobileNavigatorButton(
                    item = item,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun MobileNavigatorButton(
    item: MobileNavigatorItem,
    modifier: Modifier = Modifier,
) {
    val contentColor = if (item.selected) {
        MaterialTheme.colorScheme.primary900
    } else {
        MaterialTheme.colorScheme.dark700
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .clickable(onClick = item.onClick)
            .padding(vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Box(
            modifier = Modifier
                .size(width = 44.dp, height = 28.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    color = if (item.selected) MaterialTheme.colorScheme.secondary50 else Color.Transparent,
                ),
            contentAlignment = Alignment.Center,
        ) {
            item.icon?.invoke(item.selected) ?: MobileNavigatorDefaultIcon(
                icon = DefaultMobileNavigatorIcon,
                contentDescription = item.contentDescription,
                tint = contentColor,
            )
        }

        Text(
            text = item.title,
            color = if (item.selected) MaterialTheme.colorScheme.dark900 else contentColor,
            style = MaterialTheme.typography.inter10Normal,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun MobileNavigatorDefaultIcon(
    icon: DrawableResource,
    contentDescription: String,
    tint: Color,
) {
    Icon(
        painter = painterResource(icon),
        contentDescription = contentDescription,
        modifier = Modifier.size(20.dp),
        tint = tint,
    )
}

@Composable
@MobilePreview
private fun MobileNavigator_Preview() {
    MobileNavigator(
        items = listOf(
            MobileNavigatorItem(
                title = "Main",
                selected = true,
                onClick = {},
            ),
            MobileNavigatorItem(
                title = "Profile",
                onClick = {},
            ),
            MobileNavigatorItem(
                title = "Past",
                onClick = {},
            ),
            MobileNavigatorItem(
                title = "Share",
                onClick = {},
            ),
        ),
        modifier = Modifier.padding(top = 760.dp),
    )
}

@Composable
@MobilePreview
private fun MobileNavigator_PreviewCustomIcons() {
    MobileNavigator(
        items = listOf(
            MobileNavigatorItem(
                title = "Главная",
                onClick = {},
                icon = { isSelected ->
                    Icon(
                        painter = painterResource(Res.drawable.ic_calendar),
                        contentDescription = null,
                        tint = if (isSelected) {
                            MaterialTheme.colorScheme.primary900
                        } else {
                            MaterialTheme.colorScheme.dark700
                        },
                        modifier = Modifier.size(20.dp)
                    )
                }
            ),
            MobileNavigatorItem(
                title = "Профиль",
                onClick = {},
                icon = { isSelected ->
                    Icon(
                        painter = painterResource(Res.drawable.ic_profile),
                        contentDescription = null,
                        tint = if (isSelected) {
                            MaterialTheme.colorScheme.primary900
                        } else {
                            MaterialTheme.colorScheme.dark700
                        },
                        modifier = Modifier.size(20.dp)
                    )
                }
            ),
            MobileNavigatorItem(
                title = "Очередная кнопка перехода",
                onClick = {},
                icon = { isSelected ->
                    Icon(
                        painter = painterResource(Res.drawable.ic_chevron_right),
                        contentDescription = null,
                        tint = if (isSelected) {
                            MaterialTheme.colorScheme.primary900
                        } else {
                            MaterialTheme.colorScheme.dark700
                        },
                        modifier = Modifier.size(20.dp)
                    )
                }
            ),
            MobileNavigatorItem(
                title = "Очередная кнопка перехода",
                onClick = {},
                icon = { isSelected ->
                    Icon(
                        painter = painterResource(Res.drawable.ic_chevron_right),
                        contentDescription = null,
                        tint = if (isSelected) {
                            MaterialTheme.colorScheme.primary900
                        } else {
                            MaterialTheme.colorScheme.dark700
                        },
                        modifier = Modifier.size(20.dp)
                    )
                }
            )

        ),
        modifier = Modifier.padding(top = 760.dp),
    )
}
