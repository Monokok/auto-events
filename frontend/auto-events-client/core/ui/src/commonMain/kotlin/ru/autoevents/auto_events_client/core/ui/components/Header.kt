package ru.autoevents.auto_events_client.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import auto_events_client.core.ui.generated.resources.Res
import auto_events_client.core.ui.generated.resources.auto_events
import auto_events_client.core.ui.generated.resources.ic_chevron_left
import auto_events_client.core.ui.generated.resources.slogan
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.autoevents.auto_events_client.core.ui.theme.dark900
import ru.autoevents.auto_events_client.core.ui.theme.inter14Normal
import ru.autoevents.auto_events_client.core.ui.theme.inter16Bold
import ru.autoevents.auto_events_client.core.ui.theme.white900

@Composable
internal fun WebHeader(
    locationContent: @Composable () -> Unit = {},
    navigateBack: (() -> Unit)? = null,
    //TODO: прокинуть navigationContent для навигации в хедере и сделать отрисовку в теле функции
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 20.dp, vertical = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        navigateBack?.let { action ->
            IconButton(
                onClick = action,
                colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.white900),
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_chevron_left),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
        ) {
            Text(
                text = stringResource(Res.string.auto_events),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = stringResource(Res.string.slogan),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.inter14Normal
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        locationContent()
    }
}

@Composable
internal fun MobileHeader(
    locationContent: @Composable () -> Unit = {},
    navigateBack: (() -> Unit)? = null,
    //TODO: прокинуть navigationContent для навигации в хедере и сделать отрисовку в теле функции
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        navigateBack?.let { action ->
            IconButton(
                onClick = action,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.dark900,
                    containerColor = MaterialTheme.colorScheme.white900
                ),
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_chevron_left),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(Res.string.auto_events),
            style = MaterialTheme.typography.inter16Bold,
            color = MaterialTheme.colorScheme.dark900,
            maxLines = 2,
        )
        Spacer(modifier = Modifier.weight(1f))
        locationContent()
    }
}

@Composable
expect fun Header(locationContent: @Composable () -> Unit = {}, navigateBack: (() -> Unit)? = null)

@Preview(widthDp = 1200)

@Composable
fun HeaderPreview() {
    WebHeader()
}