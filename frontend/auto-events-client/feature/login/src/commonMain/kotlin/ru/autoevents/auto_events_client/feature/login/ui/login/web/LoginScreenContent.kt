package ru.autoevents.auto_events_client.feature.login.ui.login.web

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import ru.autoevents.auto_events_client.core.ui.theme.dark900
import ru.autoevents.auto_events_client.core.ui.theme.inter14Bold
import ru.autoevents.auto_events_client.core.ui.theme.inter14Normal
import ru.autoevents.auto_events_client.core.ui.theme.inter30ExtraBold
import ru.autoevents.auto_events_client.core.ui.theme.primary900
import ru.autoevents.auto_events_client.feature.login.ui.login.Action
import ru.autoevents.auto_events_client.feature.login.ui.login.AuthState
import ru.autoevents.auto_events_client.feature.login.ui.login.State

@Composable
fun LoginContent(
    state: State,
    onAction: (Action) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
//                .fillMaxWidth(0.5f)
                .widthIn(max = 600.dp).padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
        ) {

            //заголовочный блок
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "Войти в аккаунт организатора",
                    style = MaterialTheme.typography.inter30ExtraBold,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.dark900,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Нет аккаунта?",
                        style = MaterialTheme.typography.inter14Normal
                    )
                    Text(
                        text = "Зарегистрироваться", modifier = Modifier.clickable {
                            onAction(Action.OpenRegister)
                        }, style = MaterialTheme.typography.inter14Bold, color = MaterialTheme.colorScheme.primary900
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
                    shape = RoundedCornerShape(12.dp)
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
                    shape = RoundedCornerShape(12.dp)
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary900),
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        onAction(Action.Login(username, password))
                    },
                    content = { Text("Войти") })

                //поле ошибки
                val loginState = state.authState as? AuthState.LoggingIn
                if (loginState?.error != null) {
                    Text(
                        text = loginState.error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.inter14Normal,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }

        }
    }
}


