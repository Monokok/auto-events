package ru.autoevents.auto_events_client.feature.register.ui.register.web

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import auto_events_client.feature.register.generated.resources.Res
import auto_events_client.feature.register.generated.resources.error_happened
import org.jetbrains.compose.resources.stringResource
import ru.autoevents.auto_events_client.core.ui.components.TermsAndPrivacyTextModern
import ru.autoevents.auto_events_client.core.ui.theme.dark900
import ru.autoevents.auto_events_client.core.ui.theme.inter14Bold
import ru.autoevents.auto_events_client.core.ui.theme.inter14Normal
import ru.autoevents.auto_events_client.core.ui.theme.inter30ExtraBold
import ru.autoevents.auto_events_client.core.ui.theme.primary900
import ru.autoevents.auto_events_client.feature.register.ui.register.Action
import ru.autoevents.auto_events_client.feature.register.ui.register.State

@Composable
fun RegisterContent(
    state: State, onAction: (Action) -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
//            .fillMaxWidth(0.5f)
                .widthIn(max = 600.dp)
                .padding(horizontal = 16.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
        ) {

            //заголовочный блок
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "Создать аккаунт организатора",
                    style = MaterialTheme.typography.inter30ExtraBold,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.dark900,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Уже есть аккаунт?",
                        style = MaterialTheme.typography.inter14Normal,
                        color = MaterialTheme.colorScheme.dark900,                    )
                    Text(
                        text = "Войти",
                        modifier = Modifier.clickable{
                            onAction(Action.OpenLogin)
                        },
                        style = MaterialTheme.typography.inter14Bold,
                        color = MaterialTheme.colorScheme.primary900
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            ) {
                var username by remember { mutableStateOf("") }
                //input login
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Укажите ваш логин") },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    textStyle = MaterialTheme.typography.inter14Normal.copy(
                        color = MaterialTheme.colorScheme.dark900
                    ),
                )

                var email by remember { mutableStateOf("") }
                //input login
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Укажите ваш email") },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    textStyle = MaterialTheme.typography.inter14Normal.copy(
                        color = MaterialTheme.colorScheme.dark900
                    ),
                )

                var password by remember { mutableStateOf("") }
                //input password
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Пароль") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = MaterialTheme.typography.inter14Normal.copy(
                        color = MaterialTheme.colorScheme.dark900
                    ),
                )


                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary900),
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        onAction(Action.Register(username, email, password))
                    },
                    content = { Text("Зарегистрироваться") })
            }


            //вывод ошибки
            val registerState = state.authState as? ru.autoevents.auto_events_client.feature.register.ui.register.AuthState.Registering
            if (registerState?.error != null) {
                Text(
                    text = stringResource(Res.string.error_happened), //registerState.error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.inter14Normal,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
            HorizontalDivider(
                thickness = 1.dp, color = MaterialTheme.colorScheme.outline, modifier = Modifier.padding(vertical = 8.dp)
            )

            TermsAndPrivacyTextModern()
        }
    }
}


