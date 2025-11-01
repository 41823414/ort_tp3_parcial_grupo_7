# Referencia Rápida - Uso de Colores

## Cómo usar los colores en Composables

### En Composables (Jetpack Compose)

```kotlin
import androidx.compose.ui.res.colorResource
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.R

// Para backgrounds
Box(
    modifier = Modifier
        .fillMaxWidth()
        .background(colorResource(R.color.caribbean_green))
)

// Para textos
Text(
    text = "Mi Texto",
    color = colorResource(R.color.fence_green)
)

// Para iconos
Icon(
    painter = painterResource(R.drawable.bell),
    tint = colorResource(R.color.white)
)
```

## Paleta Completa

### ✅ Light Theme (Recomendado para modo claro)

| Color | Nombre | Hex | Uso |
|-------|--------|-----|-----|
| 🟩 | honeydew | #FFFFF3F3 | Fondos suaves, selectores |
| 🟩 | light_green | #FFDFF7E2 | Tarjetas, superficies |
| 🟢 | caribbean_green | #FF00D09E | Headers, botones primarios |
| 🔵 | light_blue | #FF6DB6FE | Iconos secundarios |
| 🔵 | vivid_blue | #FF3299FF | Valores, énfasis |
| 🔵 | ocean_blue | #FF0068FF | Botones alternativos |

### ✅ Dark Theme (Para modo oscuro)

| Color | Nombre | Hex | Uso |
|-------|--------|-----|-----|
| 🟫 | cyprus | #FF0E3E3E | Texto principal oscuro |
| 🟫 | fence_green | #FF052224 | Texto secundario oscuro |
| ⬛ | void_black | #FF031314 | Fondos oscuros |
| ⚪ | white | #FFFFFFFF | Texto en fondo oscuro |

## Ejemplos de Combinaciones

### Header (Light Theme)
```kotlin
Box(
    modifier = Modifier.background(colorResource(R.color.caribbean_green))
) {
    Text(
        text = "Welcome",
        color = Color.White
    )
}
```

### Card (Light Theme)
```kotlin
Surface(
    modifier = Modifier.fillMaxWidth(),
    color = colorResource(R.color.light_green),
    shadowElevation = 4.dp
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Contenido",
            color = colorResource(R.color.fence_green)
        )
    }
}
```

### Botón Primario
```kotlin
Button(
    modifier = Modifier.fillMaxWidth(),
    colors = ButtonDefaults.buttonColors(
        containerColor = colorResource(R.color.caribbean_green),
        contentColor = Color.White
    )
) {
    Text("Presionarme")
}
```

### Botón Secundario
```kotlin
Button(
    modifier = Modifier.fillMaxWidth(),
    colors = ButtonDefaults.buttonColors(
        containerColor = colorResource(R.color.honeydew),
        contentColor = colorResource(R.color.fence_green)
    )
) {
    Text("Secundario")
}
```

### Icono Coloreado
```kotlin
Surface(
    modifier = Modifier.size(40.dp),
    color = colorResource(R.color.light_blue),
    shape = RoundedCornerShape(8.dp)
) {
    Icon(
        painter = painterResource(R.drawable.enter),
        contentDescription = "Transacción",
        tint = Color.White
    )
}
```

### Texto Destacado (Negativo)
```kotlin
Text(
    text = "-$100.00",
    color = colorResource(R.color.vivid_blue),
    fontWeight = FontWeight.Bold
)
```

### Divider/Separador
```kotlin
Divider(
    color = colorResource(R.color.light_green).copy(alpha = 0.3f),
    thickness = 1.dp
)
```

## Valores Predefinidos

Para evitar repetir lo mismo, considera crear constantes:

```kotlin
object AppColors {
    // Light Theme
    val Primary = R.color.caribbean_green
    val PrimaryVariant = R.color.light_green
    val Secondary = R.color.light_blue
    val Surface = R.color.honeydew
    val TextPrimary = R.color.fence_green
    val TextSecondary = R.color.cyprus
    
    // Dark Theme
    val PrimaryDark = R.color.void_black
    val TextPrimaryDark = R.color.white
    val TextSecondaryDark = R.color.light_green
}
```

Luego usar:
```kotlin
Box(
    modifier = Modifier.background(colorResource(AppColors.Primary))
)
```

## Tip: Transparencia

```kotlin
colorResource(R.color.white).copy(alpha = 0.8f)  // 80% opaco
colorResource(R.color.white).copy(alpha = 0.5f)  // 50% opaco (semi-transparente)
colorResource(R.color.white).copy(alpha = 0.2f)  // 20% opaco (muy transparente)
```

## Tema Oscuro (Night Mode)

Android automáticamente usará los colores de `values-night/` cuando el dispositivo esté en modo oscuro.

Archivos:
- `values/colors.xml` → Tema Claro
- `values-night/colors.xml` → Tema Oscuro

No necesitas hacer nada especial en tu código. ¡Android lo maneja automáticamente! 🎨

