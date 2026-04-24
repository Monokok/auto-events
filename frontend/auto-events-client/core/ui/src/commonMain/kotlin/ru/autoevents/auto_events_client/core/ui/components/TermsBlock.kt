package ru.autoevents.auto_events_client.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import ru.autoevents.auto_events_client.core.ui.theme.dark900
import ru.autoevents.auto_events_client.core.ui.theme.inter14Normal
import ru.autoevents.auto_events_client.core.ui.theme.primary900

@Composable
fun TermsAndPrivacyTextModern() {
    val linkStyles = TextLinkStyles(
        style = SpanStyle(
            color = MaterialTheme.colorScheme.primary900,
            fontSize = 14.sp,           //  textStyle
            fontWeight = FontWeight.Bold, //  inter14Bold
            fontFamily = FontFamily.Default, //  FontFamily.Default
//            textDecoration = TextDecoration.Underline
        )
    )

    val annotatedString = buildAnnotatedString {

        withStyle(
            style = SpanStyle(
                fontSize = MaterialTheme.typography.inter14Normal.fontSize,
                fontWeight = MaterialTheme.typography.inter14Normal.fontWeight,
                fontFamily = MaterialTheme.typography.inter14Normal.fontFamily,
                color = MaterialTheme.colorScheme.dark900
            )
        ) {
            append("Нажимая “Зарегистрироваться”\nВы соглашаетесь с ")
        }

        withLink(
            LinkAnnotation.Url(
                url = "https://example.com/terms",
                styles = linkStyles
            )
        ) {
            append("Условиями использования")
        }

        withStyle(
            style = SpanStyle(
                fontSize = MaterialTheme.typography.inter14Normal.fontSize,
                fontWeight = MaterialTheme.typography.inter14Normal.fontWeight,
                fontFamily = MaterialTheme.typography.inter14Normal.fontFamily,
                color = MaterialTheme.colorScheme.dark900
            )
        ) {
            append(" и ")
        }

        withLink(
            LinkAnnotation.Url(
                url = "https://example.com/terms",
                styles = linkStyles
            )
        ) {
            append("Политикой конфиденциальности")
        }
    }
    Text(
        text = annotatedString,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.inter14Normal
    )
}

@Composable
@WebPreview
@MobilePreview
private fun Preview(){
    TermsAndPrivacyTextModern()
}