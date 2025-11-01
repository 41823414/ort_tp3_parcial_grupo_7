# Implementación - HomeScreen

## Descripción General
Se ha implementado la pantalla principal (HomeScreen) con una estructura de componentes reutilizables, siguiendo el diseño proporcionado en la paleta de colores light theme.

## Estructura de la Pantalla

### 1. **Header** (HomeHeader)
- Ubicado en la parte superior con fondo verde (caribbean_green)
- Contiene dos textos alineados a la izquierda:
  - "Hi, Welcome Back" (20sp, Bold, White)
  - "Good Morning" (14sp, White con 0.8 alpha)
- Icono de notificaciones (bell) a la derecha
- Altura: 24dp de padding horizontal y vertical

### 2. **Balance Section** (BalanceSection)
- Fondo verde (caribbean_green)
- **2.1 - Tarjetas de Balance y Gastos**:
  - Dos columnas con tarjetas de balance
  - Cada una muestra título y monto
  - Total Balance: $7,783.00 (White)
  - Total Expense: -$1,187.40 (Vivid Blue)
  - Fondo semi-transparente (0.1f alpha)
  
- **2.2 - Progress Bar**:
  - Barra de progreso del 30%
  - Indicador de porcentaje y monto máximo
  - Color blanco con fondo semi-transparente
  
- **2.3 - Texto de Estado**:
  - "30% Of Your Expenses, Looks Good."
  - Color blanco con transparencia

### 3. **Main Content Card** (MainContentCard)
Superficie blanca con bordes redondeados (24dp) y sombra

- **3.1 - Savings Box**:
  - Fondo verde (caribbean_green)
  - Icono de auto (car) en caja con borde blanco
  - Texto "Savings On Goals"
  - Dos items de transacción:
    - "Revenue Last Week": $4,000.00 (White)
    - "Food Last Week": -$100.00 (Vivid Blue)

- **3.2 - Period Selector**:
  - Tres botones: "Daily", "Weekly", "Monthly"
  - Fondo de contenedor: honeydew (light_green claro)
  - Botón seleccionado: caribbean_green con texto blanco
  - Botón no seleccionado: transparente con texto fence_green
  - Bordes redondeados (8dp)

- **3.3 - Transaction List**:
  - Componentes reutilizables para cada transacción
  - Cada fila contiene:
    - Icono en superficie azul (light_blue)
    - Título y fecha/hora
    - Período y monto
  - Divider entre transacciones
  - Transacciones incluidas:
    - Salary: $4,000.00
    - Groceries: -$100.00
    - Rent: -$674.40

## Componentes Reutilizables

### BalanceCard
Tarjeta flexible para mostrar balance o gastos
- Parámetros: titulo, monto, colorMonto (opcional)

### ProgressBarWithLabel
Barra de progreso con porcentaje y etiqueta de monto máximo

### TransactionItem
Columna con etiqueta y monto para el widget de Savings

### PeriodSelector
Selector de período con tres opciones

### TransactionRow
Fila individual de transacción con icono, detalles y monto

### TransactionList
Lista completa de transacciones

## Colores Utilizados (Light Theme)

```
- Fondo principal: white (#FFFFFFFF)
- Header y elementos primarios: caribbean_green (#FF00D09E)
- Fondo secundario: light_green (#FFDFF7E2)
- Botones alternos: honeydew (#FFFFF3F3)
- Iconos/Fondos secundarios: light_blue (#FF6DB6FE)
- Texto importante: fence_green (#FF052224)
- Texto secundario: cyprus (#FF0E3E3E)
- Valores negativos: vivid_blue (#FF3299FF)
```

## Características Técnicas

- **Scroll**: La pantalla es scrollable verticalmente
- **State Management**: Selección de período con `remember { mutableStateOf() }`
- **Composables Privados**: Todos los sub-componentes son private para encapsulación
- **Resources**: Utiliza `colorResource()` y `painterResource()` para mejor gestión
- **Responsive**: Utiliza weights y fillMaxWidth para adaptarse a diferentes tamaños

## Callbacks Disponibles

```kotlin
onNavigateToBalance: () -> Unit
onNavigateToTransactions: () -> Unit
onNavigateToCategories: () -> Unit
onNavigateToProfile: () -> Unit
onShowNotifications: () -> Unit
```

## Próximos Pasos (Opcional)

1. Integrar con ViewModels para datos reales
2. Agregar Dark Theme con valores-night
3. Conectar transacciones con la base de datos
4. Implementar navegación real entre pantallas
5. Agregar animaciones y transiciones

