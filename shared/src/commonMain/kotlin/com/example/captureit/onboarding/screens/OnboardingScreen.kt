package com.example.captureit.onboarding.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import captureit.shared.generated.resources.Res
import captureit.shared.generated.resources.get_started
import captureit.shared.generated.resources.post_t_and_c_txt
import captureit.shared.generated.resources.pre_t_and_c_txt
import captureit.shared.generated.resources.t_and_c_txt
import captureit.shared.generated.resources.welcome_to_app
import com.example.captureit.onboarding.helpers.Constants.CLICKABLE_TEXT_ANNOTATION
import com.example.captureit.onboarding.helpers.Constants.TERMS_AND_CONDITIONS_TAG
import com.example.captureit.onboarding.viewmodels.OnboardingViewModel
import com.example.captureit.services.navigation.LoginScreen
import com.example.commonuicomponents.FeatureTile
import com.example.commonuicomponents.carousels.ImageCarouselView
import com.example.commonuicomponents.commonWidgets.CTextButton
import com.example.commonuicomponents.commonWidgets.TextButtonType
import com.example.commonuicomponents.helpers.ScrollType
import com.example.theme.scrimLightHighContrast
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun OnboardingScreen(
    baseNavController: NavController,
    viewModel: OnboardingViewModel = koinViewModel()
) {
    val imageCarouselIndex = remember { mutableIntStateOf(0) }

    Scaffold { paddingValue ->
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (headerText, ctasLayout, onboardingCarousel, featureTiles) = createRefs()

            ImageCarouselView(
                modifier = Modifier
                    .constrainAs(onboardingCarousel) {
                        height = Dimension.fillToConstraints
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(ctasLayout.top)
                    },
                listOfImagePaths = viewModel.imagePaths,
                scrollType = ScrollType.SnapInScrolledDirection,
                indexOfItemInHighlight = imageCarouselIndex
            )

            Text(
                modifier = Modifier
                    .constrainAs(headerText) {
                        top.linkTo(parent.top, paddingValue.calculateTopPadding())
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = stringResource(Res.string.welcome_to_app),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .constrainAs(featureTiles) {
                        bottom.linkTo(ctasLayout.top)
                    }
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                scrimLightHighContrast
                            ),
                            tileMode = TileMode.Decal
                        ),
                    )
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                viewModel.indicatorTitleList.forEachIndexed { idx, title ->
                    FeatureTile(
                        modifier = Modifier
                            .width(100.dp),
                        imageSize = 40.dp,
                        imagePath = "",
                        title = title,
                        titleTextStyle = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.surfaceContainer
                        ),
                        isSelected = idx == imageCarouselIndex.value
                    )
                }
            }
            CtaLayout(
                modifier = Modifier
                    .constrainAs(ctasLayout) {
                        width = Dimension.matchParent
                        bottom.linkTo(parent.bottom)
                    },
                baseNavController = baseNavController,
                scaffoldPaddingValues = paddingValue
            )
        }
    }
}

@Composable
fun CtaLayout(
    modifier: Modifier,
    baseNavController: NavController,
    scaffoldPaddingValues: PaddingValues
) {
    val termAndConditionsStringStyle = MaterialTheme.typography.bodyMedium
    val tAndCPreTxt = stringResource(Res.string.pre_t_and_c_txt)
    val tAndCTxt = stringResource(Res.string.t_and_c_txt)
    val tAndCPostTxt = stringResource(Res.string.post_t_and_c_txt)
    val termAndConditionsString = remember {
        buildAnnotatedString {
            append(tAndCPreTxt)
            pushStringAnnotation(TERMS_AND_CONDITIONS_TAG, CLICKABLE_TEXT_ANNOTATION)
            withStyle(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = termAndConditionsStringStyle.fontWeight,
                    fontSize = termAndConditionsStringStyle.fontSize,
                    fontFamily = termAndConditionsStringStyle.fontFamily
                )
            ) { append(tAndCTxt) }
            pop()
            append(tAndCPostTxt)
        }
    }

    ConstraintLayout(
        modifier = modifier
            .background(color = Color.Black)
    ) {
        val (cta, termsAndConditions) = createRefs()

        CTextButton(
            text = stringResource(Res.string.get_started),
            type = TextButtonType.SECONDARY,
            modifier = Modifier
                .constrainAs(cta) {
                    top.linkTo(parent.top)
                    bottom.linkTo(termsAndConditions.top)
                }
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            onClick = { baseNavController.navigate(LoginScreen) },
        )

        ClickableText(
            modifier = Modifier
                .constrainAs(termsAndConditions) {
                    bottom.linkTo(parent.bottom, scaffoldPaddingValues.calculateBottomPadding())
                }
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = termAndConditionsString,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.surfaceContainerLowest
            ),
            softWrap = true,
            onClick = { offset ->
                termAndConditionsString.getStringAnnotations(
                    tag = TERMS_AND_CONDITIONS_TAG,
                    start = offset,
                    end = offset
                ).firstOrNull()?.let {
                    baseNavController.navigate(LoginScreen)
                }
            }
        )
    }
}