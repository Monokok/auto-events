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
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_NO
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import auto_events_client.core.ui.generated.resources.Res
import auto_events_client.core.ui.generated.resources.auto_events
import auto_events_client.core.ui.generated.resources.ic_chevron_left
import auto_events_client.core.ui.generated.resources.slogan
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.autoevents.auto_events_client.core.ui.theme.*

@Composable
internal fun WebHeader(
    locationContent: @Composable () -> Unit = {},
    navigationContent: @Composable () -> Unit = {},
    navigateBack: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 0.dp)
            .clip(RoundedCornerShape(50.dp))
//            .background(MaterialTheme.colorScheme.primary900)
            .background(MaterialTheme.colorScheme.diagonalGradient)
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
                style = MaterialTheme.typography.inter30ExtraBold,
                color = MaterialTheme.colorScheme.white950,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = stringResource(Res.string.slogan),
                style = MaterialTheme.typography.inter14Normal,
                color = MaterialTheme.colorScheme.white950,
            )
        }
        navigationContent()
        Spacer(modifier = Modifier.weight(1f))
        locationContent()
    }
}

@Composable
internal fun MobileHeader(
    locationContent: @Composable () -> Unit = {},
    navigationContent: @Composable () -> Unit = {},
    navigateBack: (() -> Unit)? = null,
) {
    //TODO: отрисовать navigationContent если он потребуется
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
expect fun Header(
    locationContent: @Composable () -> Unit = {},
    navigationContent: @Composable () -> Unit = {},
    navigateBack: (() -> Unit)? = null
)

@Composable
@Preview(widthDp = 1200, uiMode = UI_MODE_NIGHT_NO)
@Preview(widthDp = 1200, uiMode = UI_MODE_NIGHT_YES)
fun HeaderPreview() {
    WebHeader()
}