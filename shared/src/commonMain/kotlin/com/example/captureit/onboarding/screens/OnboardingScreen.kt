package com.example.captureit.onboarding.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import captureit.shared.generated.resources.Res
import captureit.shared.generated.resources.get_started
import captureit.shared.generated.resources.post_t_and_c_txt
import captureit.shared.generated.resources.pre_t_and_c_txt
import captureit.shared.generated.resources.t_and_c_txt
import captureit.shared.generated.resources.welcome_to_app
import com.example.captureit.onboarding.viewmodels.OnboardingViewModel
import com.example.captureit.services.navigation.OnboardingScreen
import com.example.commonuicomponents.FeatureTile
import com.example.commonuicomponents.carousels.ImageCarouselView
import com.example.commonuicomponents.helpers.ScrollType
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingScreen(
    navController: NavController,
    onboardingViewModel: OnboardingViewModel
) {
    val imageCarouselIndex = remember { mutableIntStateOf(0) }
    val termAndConditionsStringStyle = MaterialTheme.typography.bodyMedium
    val tAndCTag = "terms_and_conditions_clickable_str"
    val tAndCAnnotation = "clickable_txt"
    val tAndCPreTxt = stringResource(Res.string.pre_t_and_c_txt)
    val tAndCTxt = stringResource(Res.string.t_and_c_txt)
    val tAndCPostTxt = stringResource(Res.string.post_t_and_c_txt)
    val termAndConditionsString = remember {
        buildAnnotatedString {
            append(tAndCPreTxt)
            pushStringAnnotation(tAndCTag, tAndCAnnotation)
            withStyle(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = termAndConditionsStringStyle.fontWeight,
                    fontSize = termAndConditionsStringStyle.fontSize,
                    fontFamily = termAndConditionsStringStyle.fontFamily
                )
            ) {
                append(tAndCTxt)
            }
            pop()
            append(tAndCPostTxt)
        }
    }

    Scaffold { paddingValue ->
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValue)
        ) {
            val (headerText, cta, termsAndConditions,
                onboardingCarousel, featureTiles) = createRefs()

            ImageCarouselView(
                modifier = Modifier
                    .constrainAs(onboardingCarousel) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxSize(),
                listOfImagePaths = onboardingViewModel.imagePaths,
                scrollType = ScrollType.SnapInScrolledDirection,
                indexOfItemInHighlight = imageCarouselIndex
            )

            Text(
                modifier = Modifier
                    .constrainAs(headerText) {
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                text = stringResource(Res.string.welcome_to_app),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .constrainAs(featureTiles) {
                        bottom.linkTo(cta.top)
                    }
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                onboardingViewModel.indicatorTitleList.forEachIndexed { idx, title ->
                    FeatureTile(
                        modifier = Modifier
                            .width(100.dp),
                        imageSize = 40.dp,
                        imagePath = "",
                        title = title,
                        isSelected = idx == imageCarouselIndex.value
                    )
                }
            }

            TextButton(
                modifier = Modifier
                    .constrainAs(cta) {
                        bottom.linkTo(termsAndConditions.top)
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(12.dp)
                    ),
                onClick = { navController.navigate(OnboardingScreen) },
            ) {
                Text(
                    text = stringResource(Res.string.get_started),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primaryContainer,
                )
            }

            ClickableText(
                modifier = Modifier
                    .constrainAs(termsAndConditions) {
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = termAndConditionsString,
                style = MaterialTheme.typography.bodySmall,
                softWrap = true,
                onClick = { offset ->
                    termAndConditionsString.getStringAnnotations(
                        tag = tAndCTag,
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        navController.navigate(OnboardingScreen)
                    }
                }
            )
        }
    }
}
