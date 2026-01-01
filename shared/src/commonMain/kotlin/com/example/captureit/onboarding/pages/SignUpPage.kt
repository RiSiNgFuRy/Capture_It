package com.example.captureit.onboarding.pages

import androidx.compose.runtime.Composable
import com.example.captureit.onboarding.viewmodels.LoginSignupSharedViewModel
import com.example.captureit.onboarding.viewmodels.SignUpViewModel
import com.example.commonuicomponents.commonWidgets.CTextField
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SignUpPage(
    sharedViewModel: LoginSignupSharedViewModel,
    viewModel: SignUpViewModel = koinViewModel(),
) {
    CTextField(

    )
}