package com.example.captureit.onboarding.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import captureit.shared.generated.resources.Res
import captureit.shared.generated.resources.enter_your_phone_number
import captureit.shared.generated.resources.login_to_your_account
import com.example.captureit.onboarding.viewmodels.PhoneNumberViewModel
import com.example.captureit.utils.StringUtils
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun PhoneNumberPage(viewModel: PhoneNumberViewModel = koinViewModel()) {
    val focusRequester = remember { FocusRequester() }
    val userPhoneNumber = remember { mutableStateOf(StringUtils.EMPTY) }
    val focusManager = LocalFocusManager.current
    val isPhnNumTextEditorFocused = remember { mutableStateOf(false) }
    val isCountryCodeExpanded = remember { mutableStateOf(false) }
    var selectedCountryCode by remember { mutableStateOf("+91") }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
    ) {
        val (pageTitleTxt, subTitleTxt, countryCodeBox, numberEditBox) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(pageTitleTxt) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top, 150.dp)
                },
            text = stringResource(Res.string.login_to_your_account),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.surfaceContainerLowest
        )

        Text(
            modifier = Modifier
                .constrainAs(subTitleTxt) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(pageTitleTxt.bottom)
                },
            text = stringResource(Res.string.enter_your_phone_number),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.surfaceContainerLowest
        )

        Box(
            modifier = Modifier
                .constrainAs(countryCodeBox) {
                    height = Dimension.fillToConstraints
                    start.linkTo(subTitleTxt.start)
                    top.linkTo(numberEditBox.top)
                    bottom.linkTo(numberEditBox.bottom)
                }
                .width(80.dp)
                .border(
                    width = 2.dp,
                    color = if (isCountryCodeExpanded.value)
                        MaterialTheme.colorScheme.onSecondary
                    else
                        MaterialTheme.colorScheme.outlineVariant,
                    shape = MaterialTheme.shapes.large
                )
                .clip(MaterialTheme.shapes.large)
                .clickable { isCountryCodeExpanded.value = true }
        ) {
            Text(
                text = selectedCountryCode,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
            
            DropdownMenu(
                expanded = isCountryCodeExpanded.value,
                onDismissRequest = { isCountryCodeExpanded.value = false }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "+91 India",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    },
                    onClick = {
                        selectedCountryCode = "+91"
                        isCountryCodeExpanded.value = false
                    }
                )
            }
        }

        TextField(
            value = userPhoneNumber.value,
            onValueChange = { newValue ->
                userPhoneNumber.value = newValue
            },
            modifier = Modifier
                .constrainAs(numberEditBox) {
                    width = Dimension.fillToConstraints
                    top.linkTo(subTitleTxt.bottom, 15.dp)
                    start.linkTo(countryCodeBox.end, 12.dp)
                    end.linkTo(parent.end, 16.dp)
                }
                .focusRequester(focusRequester)
                .onFocusChanged { state ->
                    isPhnNumTextEditorFocused.value = state.hasFocus
                }
                .border(
                    width = 2.dp,
                    color = if (isPhnNumTextEditorFocused.value)
                        MaterialTheme.colorScheme.onSecondary
                    else
                        MaterialTheme.colorScheme.outlineVariant,
                    shape = MaterialTheme.shapes.large
                )
                .clip(shape = MaterialTheme.shapes.large),
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true,
            shape = MaterialTheme.shapes.small,
            keyboardOptions = KeyboardOptions(
                showKeyboardOnFocus = true
            ),
            colors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.scrim
            )
        )
    }
}
