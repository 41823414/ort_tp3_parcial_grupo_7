# 🎯 Mejores Prácticas - HomeScreen

## Estructura de Componentes

### ✅ Buena Práctica: Componentes Pequeños y Reutilizables

```kotlin
// ✅ BIEN: Componente reutilizable
@Composable
private fun BalanceCard(
    title: String,
    amount: String,
    amountColor: Color = Color.White,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = Color.White.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp)
    ) {
        Text(text = title, fontSize = 12.sp)
        Text(text = amount, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = amountColor)
    }
}

// ❌ MAL: Todo en un componente monolítico
@Composable
fun HomeScreenMonolithic() {
    // 500 líneas de código aquí...
}
```

### ✅ Buena Práctica: Uso de `remember` para State

```kotlin
// ✅ BIEN: State manejado localmente
@Composable
fun HomeScreen() {
    var selectedPeriod by remember { mutableStateOf("Monthly") }
    
    PeriodSelector(
        selectedPeriod = selectedPeriod,
        onPeriodChange = { selectedPeriod = it }
    )
}

// ❌ MAL: State en nivel superior (si no es necesario)
var globalPeriod = "Monthly"  // ❌ Variables globales

@Composable
fun HomeScreen() {
    // Usar globalPeriod aquí
}
```

### ✅ Buena Práctica: Recursos vs Hardcoding

```kotlin
// ✅ BIEN: Usar recursos
Text(
    text = "Hi, Welcome Back",
    color = colorResource(R.color.caribbean_green)
)

// ❌ MAL: Valores hardcodeados
Text(
    text = "Hi, Welcome Back",
    color = Color(0xFF00D09E)  // ❌ Difícil de mantener
)
```

## Organización de Archivos

```
ui/
├── screens/
│   └── HomeScreen.kt          ← Pantalla principal
├── components/
│   ├── BottomNavigationBar.kt ← Componentes reutilizables
│   └── CommonComponents.kt    ← Componentes comunes
├── theme/
│   └── ...                    ← Temas globales
└── viewmodel/
    └── ...                    ← ViewModels

res/
├── values/
│   ├── colors.xml            ← Paleta de colores
│   ├── themes.xml            ← Temas
│   └── strings.xml           ← Textos
└── values-night/
    └── colors.xml            ← Colores modo oscuro
```

## Performance

### ✅ Buena Práctica: Prevenir Recomposiciones

```kotlin
// ✅ BIEN: Componentes simples que no se recomponen innecesariamente
@Composable
private fun BalanceCard(
    title: String,
    amount: String,
    modifier: Modifier = Modifier
) {
    // El componente solo se recompone si sus parámetros cambian
    Column(modifier = modifier) {
        Text(text = title)
        Text(text = amount)
    }
}

// ❌ MAL: Crear lambdas en cada recomposición
@Composable
private fun BalanceCard(
    onClickAction: () -> Unit
) {
    Button(onClick = { onClickAction() }) {  // ❌ Nueva lambda cada vez
        Text("Click me")
    }
}

// ✅ MEJOR:
@Composable
private fun BalanceCard(
    onClickAction: () -> Unit
) {
    Button(onClick = onClickAction) {  // ✅ Usa directamente
        Text("Click me")
    }
}
```

### ✅ Buena Práctica: Lazy Lists para Contenido Dinámico

```kotlin
// Para listas grandes, usar LazyColumn en lugar de Column
@Composable
private fun TransactionList(
    transactions: List<Transaction>
) {
    LazyColumn {
        items(transactions) { transaction ->
            TransactionRow(transaction = transaction)
        }
    }
}
```

## State Management

### ✅ Recomendación: Usar ViewModel para Lógica Compleja

```kotlin
// En un futuro, considerar:
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val balance by viewModel.balance.collectAsState()
    val transactions by viewModel.transactions.collectAsState()
    val selectedPeriod by viewModel.selectedPeriod.collectAsState()
    
    // Usar los datos del ViewModel
}

class HomeViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {
    val balance: StateFlow<String> = ...
    val transactions: StateFlow<List<Transaction>> = ...
    val selectedPeriod: MutableStateFlow<String> = MutableStateFlow("Monthly")
}
```

## Accesibilidad (Accessibility)

```kotlin
// ✅ BIEN: Contentdescription para iconos
Icon(
    painter = painterResource(R.drawable.bell),
    contentDescription = "Notificaciones",  // ✅ Importante para screen readers
    tint = Color.White
)

// ❌ MAL: Sin contentDescription
Icon(
    painter = painterResource(R.drawable.bell),
    contentDescription = null,  // ❌ Inaccesible para discapacitados visuales
    tint = Color.White
)

// ✅ BIEN: Usar semantics
Button(
    onClick = { /* ... */ },
    modifier = Modifier.semantics {
        contentDescription = "Cambiar a período semanal"
    }
) {
    Text("Semanal")
}
```

## Testing

```kotlin
// Para futuro: Agregar tests
@Composable
fun HomeScreenTest() {
    val testTag = "home_screen"
    
    Box(modifier = Modifier.testTag(testTag)) {
        HomeScreen()
    }
}

// En el test:
@Test
fun homeScreenLoads() {
    composeTestRule.setContent {
        HomeScreenTest()
    }
    
    composeTestRule.onNodeWithTag("home_screen").assertIsDisplayed()
}
```

## Modularidad

### ✅ Estructura Propuesta

```
com.example.app/
├── data/
│   ├── repository/          ← Acceso a datos
│   ├── datasource/          ← Fuentes de datos (local, remoto)
│   └── model/               ← Modelos de datos
├── domain/
│   ├── model/               ← Entidades de negocio
│   └── usecase/             ← Lógica de negocio
├── ui/
│   ├── screens/             ← Pantallas
│   ├── components/          ← Componentes reutilizables
│   ├── theme/               ← Temas
│   └── viewmodel/           ← ViewModels
└── navigation/              ← Rutas de navegación
```

## Documentación

### ✅ Comentarios KDoc

```kotlin
/**
 * Tarjeta reutilizable para mostrar Balance o Expense
 *
 * @param title El título de la tarjeta (e.g., "Total Balance")
 * @param amount El monto a mostrar (e.g., "$7,783.00")
 * @param amountColor Color del monto (default: White)
 * @param modifier Modifier para customizar el layout
 *
 * @see BalanceSection
 */
@Composable
private fun BalanceCard(
    title: String,
    amount: String,
    amountColor: Color = Color.White,
    modifier: Modifier = Modifier
) {
    // Implementación...
}
```

## Escalabilidad

### 🚀 Para Cuando Cresca la Aplicación

1. **Extraer a ViewModel**
   ```kotlin
   class HomeViewModel : ViewModel() {
       // Lógica de negocio aquí
   }
   ```

2. **Usar Dependency Injection (Hilt)**
   ```kotlin
   @Composable
   fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
       // ViewModel inyectado automáticamente
   }
   ```

3. **Separar en múltiples archivos**
   ```
   HomeScreen.kt          ← Composable principal
   HomeScreenComponents.kt ← Sub-componentes
   HomeScreenState.kt     ← State management
   ```

4. **Añadir tests**
   ```
   HomeScreenTest.kt
   HomeViewModelTest.kt
   ```

## Checklist Final

- ✅ Componentes reutilizables
- ✅ Recursos en lugar de hardcoding
- ✅ State management adecuado
- ✅ Documentación KDoc
- ✅ Content descriptions para accesibilidad
- ✅ Naming consistente
- ✅ Modularidad
- ✅ Preparado para testing
- ✅ Preparado para inyección de dependencias
- ✅ Compatible con tema oscuro

