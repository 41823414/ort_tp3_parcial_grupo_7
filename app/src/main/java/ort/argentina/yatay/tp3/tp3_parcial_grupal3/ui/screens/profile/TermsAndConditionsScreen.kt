@file:OptIn(ExperimentalMaterial3Api::class)

package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.foundation.text.ClickableText
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.FlechaTituloPrincipalCampana
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.components.BotonSimple

@Composable
fun TermsAndConditionsScreen(
    onNavigateBack: () -> Unit = {}
) {
    val primaryColor = Color(0xFF00C896) // Verde principal

    // Texto de términos y condiciones
    val termsAndConditionsText = """
Lorem ipsum dolor sit amet. Et odio officia aut voluptate internos est omnis vitae ut architecto sunt non tenetur fuga ut provident vero. Quo aspernatur facere et consectetur ipsum et facere corrupti est asperiores facere. Est fugiat assumenda aut reprehenderit voluptatem sed.

1.Ea voluptates omnis aut sequi sequi.
2.Est dolore quae in aliquid ducimus et autem repellendus.
3.Aut ipsum Quis qui porro quasi aut minus placeat!
4.Sit consequatur neque ab vitae facere.

Aut quidem accusantium nam alias autem eum officiis placeat et omnis autem id officiis perspiciatis qui corrupti officia eum aliquam provident. Eum voluptas error et optio dolorum cum molestiae nobis et odit molestiae quo magnam impedit sed fugiat nihil non nihil vitae.
 
●Aut fuga sequi eum voluptatibus provident.
●Eos consequuntur voluptas vel amet eaque aut dignissimos velit.

Vel exercitationem quam vel eligendi rerum At harum obcaecati et nostrum beatae? Ea accusantium dolores qui rerum aliquam est perferendis mollitia et ipsum ipsa qui enim autem At corporis sunt. Aut odit quisquam est reprehenderit itaque aut accusantium dolor qui neque repellat.
    """.trimIndent()

    // Fuente Poppins
    val poppinsFontFamily = FontFamily(
        Font(R.font.poppins_light, FontWeight.Light),
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_semibold, FontWeight.SemiBold)
    )

    Scaffold(
        topBar = {
            FlechaTituloPrincipalCampana(
                titulo = "Terms And Conditions",
                onBack = onNavigateBack,
                onNotifications = { /* notificaciones */ },
                primaryColor = primaryColor
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(primaryColor)
                .padding(innerPadding)
        ) {
            // Contenedor blanco (Surface) ajustado - se extiende hasta abajo cubriendo los botones de navegación
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(Alignment.BottomCenter)
                    .padding(top = 40.dp),
                shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp, bottomStart = 0.dp, bottomEnd = 0.dp),
                color = Color(0xFFF1FFF3),
                shadowElevation = 0.dp
            ) {
                // Contenido del Surface con scroll
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 32.dp)
                        .padding(top = 50.dp, bottom = 24.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Est fugiat assumenda aut reprehenderit",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = poppinsFontFamily,
                        color = Color(0xFF000000),
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                    Text(
                        text = termsAndConditionsText,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light,
                        fontFamily = poppinsFontFamily,
                        color = Color(0xFF000000),
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    val linkColor = Color(0xFF6DB6FE)
                    val annotatedText = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFF000000),
                                fontFamily = poppinsFontFamily,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Normal
                            )
                        ) {
                            append("Read the terms and conditions in more detail at ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = linkColor,
                                fontFamily = poppinsFontFamily,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Normal,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append("www.finwiseapp.de")
                        }
                    }

                    ClickableText(
                        text = annotatedText,
                        onClick = { offset ->
                            // Aquí puedes agregar la lógica para abrir el enlace
                        },
                        modifier = Modifier.padding(bottom = 20.dp)
                    )

                    // Checkbox con texto "I accept all the terms and conditions"
                    var isChecked by remember { mutableStateOf(false) }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isChecked = !isChecked }
                            .padding(bottom = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(
                                id = if (isChecked)
                                    R.drawable.checkbox_marcado
                                else
                                    R.drawable.checkbox_desmarcado
                            ),
                            contentDescription = "Checkbox",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "I accept all the terms and conditions",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = poppinsFontFamily,
                            color = Color(0xFF000000)
                        )
                    }

                    // Accept Button - centrado y con ancho reducido
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        BotonSimple(
                            texto = "Accept",
                            colorFondo = primaryColor,
                            fillMaxWidth = 1.0f,
                            height = 50,
                            fontSize = 20,
                            onClick = onNavigateBack
                        )
                    }
                }
            }
        }
    }
}


