package ort.argentina.yatay.tp3.tp3_parcial_grupal3.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

// Proveedor de fuentes de Google Fonts usando el certificado existente
val poppinsProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

// Definir Poppins desde Google Fonts
val poppinsFont = GoogleFont("Poppins")

// Crear familia de fuentes Poppins desde Google Fonts
val poppinsFamily = FontFamily(
    Font(
        googleFont = poppinsFont,
        fontProvider = poppinsProvider,
        weight = FontWeight.Normal
    ),
    Font(
        googleFont = poppinsFont,
        fontProvider = poppinsProvider,
        weight = FontWeight.Medium
    ),
    Font(
        googleFont = poppinsFont,
        fontProvider = poppinsProvider,
        weight = FontWeight.SemiBold
    ),
    Font(
        googleFont = poppinsFont,
        fontProvider = poppinsProvider,
        weight = FontWeight.Bold
    )
)