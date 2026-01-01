package com.example.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import captureit.theme.generated.resources.Nunito_Black
import captureit.theme.generated.resources.Nunito_BlackItalic
import captureit.theme.generated.resources.Nunito_Bold
import captureit.theme.generated.resources.Nunito_BoldItalic
import captureit.theme.generated.resources.Nunito_ExtraBold
import captureit.theme.generated.resources.Nunito_ExtraBoldItalic
import captureit.theme.generated.resources.Nunito_ExtraLight
import captureit.theme.generated.resources.Nunito_ExtraLightItalic
import captureit.theme.generated.resources.Nunito_Italic
import captureit.theme.generated.resources.Nunito_Light
import captureit.theme.generated.resources.Nunito_LightItalic
import captureit.theme.generated.resources.Nunito_Medium
import captureit.theme.generated.resources.Nunito_MediumItalic
import captureit.theme.generated.resources.Nunito_Regular
import captureit.theme.generated.resources.Nunito_SemiBold
import captureit.theme.generated.resources.Nunito_SemiBoldItalic
import captureit.theme.generated.resources.Res
import com.example.theme.helpers.txtDp
import org.jetbrains.compose.resources.Font

@Composable
fun getTypography(): Typography {
    val nunitoFamily = FontFamily(
        Font(Res.font.Nunito_Black, weight = FontWeight.Black),
        Font(Res.font.Nunito_BlackItalic, weight = FontWeight.Black, style = FontStyle.Italic),
        Font(Res.font.Nunito_Bold, weight = FontWeight.Bold),
        Font(Res.font.Nunito_BoldItalic, weight = FontWeight.Bold, style = FontStyle.Italic),
        Font(Res.font.Nunito_ExtraBold, weight = FontWeight.ExtraBold),
        Font(Res.font.Nunito_ExtraBoldItalic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
        Font(Res.font.Nunito_ExtraLight, weight = FontWeight.ExtraLight),
        Font(Res.font.Nunito_ExtraLightItalic, weight = FontWeight.ExtraLight, style = FontStyle.Italic),
        Font(Res.font.Nunito_Italic, style = FontStyle.Italic),
        Font(Res.font.Nunito_Light, weight = FontWeight.Light),
        Font(Res.font.Nunito_LightItalic, weight = FontWeight.Light, style = FontStyle.Italic),
        Font(Res.font.Nunito_Medium, weight = FontWeight.Medium),
        Font(Res.font.Nunito_MediumItalic, weight = FontWeight.Medium, style = FontStyle.Italic),
        Font(Res.font.Nunito_Regular),
        Font(Res.font.Nunito_SemiBold, weight = FontWeight.SemiBold),
        Font(Res.font.Nunito_SemiBoldItalic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    )

    val baseline = Typography()

    return Typography(
        displayLarge = baseline.displayLarge.copy(
            fontFamily = nunitoFamily,
            fontSize = 28.txtDp,
        ),
        displayMedium = baseline.displayMedium.copy(
            fontFamily = nunitoFamily,
            fontSize = 18.txtDp,
        ),
        displaySmall = baseline.displaySmall.copy(
            fontFamily = nunitoFamily,
            fontSize = 12.txtDp,
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
        labelLarge =  baseline.labelLarge.copy(
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