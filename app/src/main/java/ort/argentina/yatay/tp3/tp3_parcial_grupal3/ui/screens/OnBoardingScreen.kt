@file:OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)

package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

data class OnboardingPageUi(
    val title: String,
    val imageRes: Int
)

@Composable
fun OnBoardingScreen(
    onNavigateToLogin: () -> Unit = {},
    onSkip: () -> Unit = {}
) {
    val isDark = isSystemInDarkTheme()

    // LIGHT
    val topLight = Color(0xFF00B686)
    val panelLight = Color(0xFFEAF7EF)
    val circleLight = Color(0xFFD9F0E4)
    val textLight = Color(0xFF1C2934)
    val nextLight = Color(0xFF1C2934)
    val dotALight = Color(0xFF00B686)
    val dotILight = Color(0xFF00B686).copy(alpha = 0.25f)

    // DARK (como tu mock)
    val topDark = Color(0xFF042F31)          // parte de arriba
    val panelDark = Color(0xFF064043)        // parte de abajo, un pelín más clara
    val circleDark = Color(0xFFEAF7EF)       // círculo sigue claro
    val textDark = Color(0xFFF7EEDC)         // blanco cálido
    val nextDark = Color(0xFFF7EEDC)
    val dotADark = Color(0xFFF7EEDC)
    val dotIDark = Color(0xFFF7EEDC).copy(alpha = 0.25f)

    val topColor = if (isDark) topDark else topLight
    val panelColor = if (isDark) panelDark else panelLight
    val circleColor = if (isDark) circleDark else circleLight
    val titleColor = if (isDark) textDark else textLight
    val nextColor = if (isDark) nextDark else nextLight
    val dotActive = if (isDark) dotADark else dotALight
    val dotInactive = if (isDark) dotIDark else dotILight
    val skipColor = Color.White

    val pages = listOf(
        OnboardingPageUi(
            "Welcome To\nExpense Manager",
            R.drawable.onboarding_1
        ),
        OnboardingPageUi(
            "¿Are You Ready To\nTake Control Of Your Finances?",
            R.drawable.onboarding_2
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(topColor)
    ) {
        // panel con otro tono también en dark
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 280.dp)
                .clip(RoundedCornerShape(topStart = 90.dp, topEnd = 90.dp))
                .background(panelColor)
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // HEADER
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    Text(
                        text = pages[page].title,
                        color = titleColor,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 28.dp)
                    )
                    Text(
                        text = "Skip",
                        color = skipColor,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                            .clickable { onSkip() }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // IMAGEN
                Box(
                    modifier = Modifier
                        .size(280.dp)
                        .background(circleColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = pages[page].imageRes),
                        contentDescription = null,
                        modifier = Modifier.size(230.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(56.dp))

                Text(
                    text = "Next",
                    color = nextColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .clickable {
                            if (pagerState.currentPage < pages.lastIndex) {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            } else {
                                onNavigateToLogin()
                            }
                        }
                )

                Spacer(modifier = Modifier.height(14.dp))

                PageIndicator(
                    total = pages.size,
                    current = pagerState.currentPage,
                    active = dotActive,
                    inactive = dotInactive
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun PageIndicator(
    total: Int,
    current: Int,
    active: Color,
    inactive: Color
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(total) { index ->
            Box(
                modifier = Modifier
                    .size(if (index == current) 14.dp else 10.dp)
                    .clip(CircleShape)
                    .background(if (index == current) active else inactive)
            )
        }
    }
}