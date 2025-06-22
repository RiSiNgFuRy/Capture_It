package com.example.captureit.onboarding.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import captureit.shared.generated.resources.Res
import captureit.shared.generated.resources.send_otp
import captureit.shared.generated.resources.verify
import com.example.captureit.onboarding.helpers.route
import com.example.captureit.onboarding.navigation.LoginSignUpPagesNavHost
import com.example.captureit.onboarding.navigation.OtpPage
import com.example.captureit.onboarding.navigation.PhoneNumberPage
import com.example.captureit.utils.StringUtils
import com.example.commonuicomponents.commonWidgets.CTextButton
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(
    baseNavHostController: NavHostController
) {
    val focusManager = LocalFocusManager.current
    val loginNavHostController = rememberNavController()
    val currentRoute = loginNavHostController.route()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.inverseSurface
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) {
                    focusManager.clearFocus()
                }
        ) {
            val (page, cta) = createRefs()

            LoginSignUpPagesNavHost(
                modifier = Modifier
                    .constrainAs(page) {
                        width = Dimension.fillToConstraints
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(cta.top, 16.dp)
                    },
                navHostController = loginNavHostController
            )

            CTextButton(
                modifier = Modifier
                    .constrainAs(cta) {
                        width = Dimension.fillToConstraints
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                        bottom.linkTo(parent.bottom, 16.dp)
                    },
                text = when(currentRoute) {
                    PhoneNumberPage.route() -> stringResource(Res.string.send_otp)
                    OtpPage.route() -> stringResource(Res.string.verify)
                    else -> StringUtils.EMPTY
                },
                onClick = {
                    when(currentRoute) {
                        PhoneNumberPage.route() -> {
                            loginNavHostController.navigate(OtpPage)
                        }
                        OtpPage.route() -> {

                        }
                    }
                    focusManager.clearFocus()
                }
            )
        }
    }
}