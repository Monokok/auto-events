package ru.autoevents.auto_events_client.feature.home.ui.profile.web

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.autoevents.auto_events_client.core.ui.theme.dark900
import ru.autoevents.auto_events_client.core.ui.theme.inter14Bold
import ru.autoevents.auto_events_client.core.ui.theme.inter30ExtraBold
import ru.autoevents.auto_events_client.core.ui.theme.primary900
import ru.autoevents.auto_events_client.feature.home.ui.profile.Action
import ru.autoevents.auto_events_client.feature.home.ui.profile.AuthState
import ru.autoevents.auto_events_client.feature.home.ui.profile.LoginScreen
import ru.autoevents.auto_events_client.feature.home.ui.profile.RegisterScreen
import ru.autoevents.auto_events_client.feature.home.ui.profile.State
import ru.autoevents.auto_events_client.feature.home.ui.profile.TermsAndPrivacyTextModern

@Composable
fun ProfileContent(
    state: State,
    onAction: (Action) -> Unit
) {

    when (val authState = state.authState) {

        //гость зашел — у него 2 варианта: логин или регистрация
        is AuthState.Unauthorized -> {

            //В компоненте регистрации есть кнопка "Вход" - LoginScreen
            RegisterScreen(
                state = state,
                onAction = onAction
            )

            //TODO: LoginComponent()
        }

        //пользователь на экране регистрации
        is AuthState.Registering -> {
            RegisterScreen(
                state = state,
                onAction = onAction,
            )
        }

        //пользователь находится на экране логина и вводит данные
        is AuthState.LoggingIn -> {
            LoginScreen(
                state = state,
                onAction = onAction,
            )
        }

        //
        is AuthState.Authorized -> {
            Column {
                Text("You are authorized", fontWeight = FontWeight.Bold)
                Text("${state.authState.profile.id} ${state.authState.profile.email} ${state.authState.profile.username} ${state.authState.profile.role} ")
            }


            //TODO: OrganizerComponent()
        }

        else -> {

        }
    }
}


