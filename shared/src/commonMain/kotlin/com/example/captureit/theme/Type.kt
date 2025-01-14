package com.example.captureit.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import captureit.shared.generated.resources.Nunito_Black
import captureit.shared.generated.resources.Nunito_BlackItalic
import captureit.shared.generated.resources.Nunito_Bold
import captureit.shared.generated.resources.Nunito_BoldItalic
import captureit.shared.generated.resources.Nunito_ExtraBold
import captureit.shared.generated.resources.Nunito_ExtraBoldItalic
import captureit.shared.generated.resources.Nunito_ExtraLight
import captureit.shared.generated.resources.Nunito_ExtraLightItalic
import captureit.shared.generated.resources.Nunito_Italic
import captureit.shared.generated.resources.Nunito_Light
import captureit.shared.generated.resources.Nunito_LightItalic
import captureit.shared.generated.resources.Nunito_Medium
import captureit.shared.generated.resources.Nunito_MediumItalic
import captureit.shared.generated.resources.Nunito_Regular
import captureit.shared.generated.resources.Nunito_SemiBold
import captureit.shared.generated.resources.Nunito_SemiBoldItalic
import captureit.shared.generated.resources.Res
import com.example.captureit.commonHelpers.txtDp
import org.jetbrains.compose.resources.Font

@Composable
fun getTypography(): Typography {
    val nunitoFamily = FontFamily(
        Font(Res.font.Nunito_Black),
        Font(Res.font.Nunito_BlackItalic, style = FontStyle.Italic),
        Font(Res.font.Nunito_Bold),
        Font(Res.font.Nunito_BoldItalic, style = FontStyle.Italic),
        Font(Res.font.Nunito_ExtraBold),
        Font(Res.font.Nunito_ExtraBoldItalic, style = FontStyle.Italic),
        Font(Res.font.Nunito_ExtraLight),
        Font(Res.font.Nunito_ExtraLightItalic, style = FontStyle.Italic),
        Font(Res.font.Nunito_Italic, style = FontStyle.Italic),
        Font(Res.font.Nunito_Light),
        Font(Res.font.Nunito_LightItalic, style = FontStyle.Italic),
        Font(Res.font.Nunito_Medium),
        Font(Res.font.Nunito_MediumItalic, style = FontStyle.Italic),
        Font(Res.font.Nunito_Regular),
        Font(Res.font.Nunito_SemiBold),
        Font(Res.font.Nunito_SemiBoldItalic, style = FontStyle.Italic),
    )

    val baseline = Typography()

    return Typography(
        displayLarge = baseline.displayLarge.copy(
            fontFamily = nunitoFamily,
            fontSize = 22.txtDp,
        ),
        displayMedium = baseline.displayMedium.copy(
            fontFamily = nunitoFamily,
            fontSize = 14.txtDp,
        ),
        displaySmall = baseline.displaySmall.copy(
            fontFamily = nunitoFamily,
            fontSize = 8.txtDp,
        ),
        headlineLarge = baseline.headlineLarge.copy(
            fontFamily = nunitoFamily,
            fontSize = 20.txtDp,
        ),
        headlineMedium = baseline.headlineMedium.copy(
            fontFamily = nunitoFamily,
            fontSize = 18.txtDp,
        ),
        headlineSmall = baseline.headlineSmall.copy(
            fontFamily = nunitoFamily,
            fontSize = 16.txtDp,
        ),
        titleLarge = baseline.titleLarge.copy(
            fontFamily = nunitoFamily,
            fontSize = 18.txtDp,
        ),
        titleMedium = baseline.titleMedium.copy(
            fontFamily = nunitoFamily,
            fontSize = 16.txtDp,
        ),
        titleSmall = baseline.titleSmall.copy(
            fontFamily = nunitoFamily,
            fontSize = 14.txtDp,
        ),
        bodyLarge = baseline.bodyLarge.copy(
            fontFamily = nunitoFamily,
            fontSize = 14.txtDp,
        ),
        bodyMedium = baseline.bodyMedium.copy(
            fontFamily = nunitoFamily,
            fontSize = 12.txtDp,
        ),
        bodySmall = baseline.bodySmall.copy(
            fontFamily = nunitoFamily,
            fontSize = 10.txtDp,
        ),
        labelLarge =  baseline.labelLarge .copy(
            fontFamily = nunitoFamily,
            fontSize = 10.txtDp,
        ),
        labelMedium = baseline.labelMedium.copy(
            fontFamily = nunitoFamily,
            fontSize = 8.txtDp,
        ),
        labelSmall = baseline.labelSmall.copy(
            fontFamily = nunitoFamily,
            fontSize = 6.txtDp,
        ),
    )
}