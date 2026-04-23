package ru.autoevents.auto_events_client.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import auto_events_client.core.ui.generated.resources.*
import org.jetbrains.compose.resources.Font

private val InterFontFamily
    @Composable
    get() = FontFamily(
        Font(resource = Res.font.inter_regular, weight = FontWeight.Normal),
        Font(resource = Res.font.inter_medium, weight = FontWeight.Medium),
        Font(resource = Res.font.inter_bold, weight = FontWeight.Bold),
        Font(resource = Res.font.inter_black, weight = FontWeight.Black),
    )


val Typography.inter48ExtraBold
    @Composable
    get() = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 48.sp,
    )

val Typography.inter20Bold
    @Composable
    get() = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    )

val Typography.inter16Bold
    @Composable
    get() = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    )

val Typography.inter14Normal
    @Composable
    get() = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    )

val Typography.inter14Link
    @Composable
    get() = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        textDecoration = TextDecoration.Underline,
    )

val Typography.inter14Bold
    @Composable
    get() = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
    )

val Typography.inter12Normal
    @Composable
    get() = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    )

val Typography.inter10Normal
    @Composable
    get() = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    )

val Typography.inter30ExtraBold
    @Composable
    get() = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp,
        lineHeight = (40.0 / 30.0).em,
        letterSpacing = 0.sp
    )
