package ru.autoevents.auto_events_client.feature.home.ui.mainScreen.web

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import auto_events_client.core.ui.generated.resources.Res
import auto_events_client.core.ui.generated.resources.about_us
import auto_events_client.core.ui.generated.resources.for_moderator
import auto_events_client.core.ui.generated.resources.for_organizer
import auto_events_client.core.ui.generated.resources.ic_calendar
import auto_events_client.core.ui.generated.resources.ic_past
import auto_events_client.core.ui.generated.resources.ic_profile
import auto_events_client.core.ui.generated.resources.ic_share
import auto_events_client.core.ui.generated.resources.main_page
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.primary).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        FooterItemButton(stringResource(Res.string.main_page), {
            Image(
                painter = painterResource(Res.drawable.ic_calendar),
                contentScale = ContentScale.Crop,
                contentDescription = "Дата",
                modifier = Modifier.size(16.dp)
            )
        }, {})
        FooterItemButton(stringResource(Res.string.for_organizer), {
            Image(
                painter = painterResource(Res.drawable.ic_profile),
                contentScale = ContentScale.Crop,
                contentDescription = "Дата",
                modifier = Modifier.size(16.dp)
            )
        }, {})
        FooterItemButton(
            stringResource(Res.string.for_moderator), {
                Image(
                    painter = painterResource(Res.drawable.ic_past),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Дата",
                    modifier = Modifier.size(16.dp)
                )
            }, {})
        FooterItemButton(
            stringResource(Res.string.about_us), {
                Image(
                    painter = painterResource(Res.drawable.ic_share),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Дата",
                    modifier = Modifier.size(16.dp)
                )
            }, {})
    }
}

@Composable
fun FooterItemButton(
    text: String = "Button", icon: (@Composable () -> Unit)? = null, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick, modifier = modifier, colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.primary // цвет текста
        ), border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary), // рамка
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.invoke()
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(text = text)
    }

}


@Preview
@Composable
fun FooterItemButtonPreview() {
    FooterItemButton("Кнопка", onClick = {})
}

@Composable
@Preview(widthDp = 1200)
fun FooterPreview() {
    Footer()
}