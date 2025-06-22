package com.example.captureit.onboarding.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import captureit.shared.generated.resources.Res
import captureit.shared.generated.resources.enter_your_otp
import captureit.shared.generated.resources.verify_your_otp
import com.example.captureit.onboarding.viewmodels.OtpViewModel
import com.example.captureit.utils.StringUtils
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun OtpPage(viewModel: OtpViewModel = koinViewModel()) {
    val focusManager = LocalFocusManager.current
    val isOtpFieldFocused = remember { mutableStateOf(false) }
    val otpFields = remember { List(6) { mutableStateOf(StringUtils.EMPTY) } }
    val focusRequesters = remember { List(6) { FocusRequester() } }

    // Request focus on the first field when the page loads
    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
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
        val (pageTitleTxt, subTitleTxt, otpRow) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(pageTitleTxt) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top, 150.dp)
                },
            text = stringResource(Res.string.verify_your_otp),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.surfaceContainerLowest
        )

        Text(
            modifier = Modifier
                .constrainAs(subTitleTxt) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(pageTitleTxt.bottom)
                },
            text = stringResource(Res.string.enter_your_otp),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.surfaceContainerLowest
        )

        Row(
            modifier = Modifier
                .constrainAs(otpRow) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                    top.linkTo(subTitleTxt.bottom, 16.dp)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            otpFields.forEachIndexed { index, state ->
                TextField(
                    value = state.value,
                    onValueChange = { newValue ->
                        when {
                            newValue.isNotEmpty() && newValue.all { it.isDigit() } -> {
                                state.value = newValue.last().toString()
                                if (index < 5) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .size(60.dp)
                        .wrapContentSize()
                        .padding(end = 12.dp)
                        .focusRequester(focusRequesters[index])
                        .onFocusChanged { focusState ->
                            isOtpFieldFocused.value = focusState.hasFocus
                        }
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.Backspace) {
                                otpFields[index].value = StringUtils.EMPTY
                                if (index > 0) {
                                    focusRequesters[index - 1].requestFocus()
                                }
                                false
                            } else {
                                false
                            }
                        }
                        .border(
                            width = 2.dp,
                            color = if (isOtpFieldFocused.value)
                                MaterialTheme.colorScheme.onSecondary
                            else
                                MaterialTheme.colorScheme.outlineVariant,
                            shape = MaterialTheme.shapes.large
                        )
                        .clip(shape = MaterialTheme.shapes.large),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        textAlign = TextAlign.Center
                    ),
                    colors = TextFieldDefaults.colors(
                        cursorColor = MaterialTheme.colorScheme.scrim
                    ),
                    placeholder = {
                        Text(
                            "0",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.outlineVariant,
                        )
                    },
                    singleLine = true
                )
            }
        }
    }
}
