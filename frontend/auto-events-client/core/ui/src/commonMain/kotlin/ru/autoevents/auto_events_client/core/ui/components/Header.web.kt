package ru.autoevents.auto_events_client.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import auto_events_client.core.ui.generated.resources.Res
import auto_events_client.core.ui.generated.resources.auto_events
import auto_events_client.core.ui.generated.resources.slogan
import org.jetbrains.compose.resources.stringResource
import ru.autoevents.auto_events_client.core.ui.theme.inter14Normal

@Composable
fun Header(
    locationContent: @Composable () -> Unit = {},
    //TODO: прокинуть navigationContent для навигации в хедере и сделать отрисовку в теле функции
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 20.dp).clip(RoundedCornerShape(50.dp))
            .background(MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.padding(start = 60.dp, top = 30.dp, bottom = 30.dp, end = 20.dp)) {
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
        locationContent()
//        ButtonLocation(modifier = Modifier.padding(end = 30.dp))
    }
}

@Preview(widthDp = 1200)
@Composable
fun HeaderPreview() {
    Header()
}