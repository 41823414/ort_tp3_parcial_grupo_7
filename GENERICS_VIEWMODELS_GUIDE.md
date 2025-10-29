# 🎯 Generics + ViewModels - Guía Completa

## 📚 ¿Qué son los Generics?

**Generics** (tipos genéricos) permiten escribir código reutilizable que funciona con diferentes tipos de datos manteniendo la seguridad de tipos en tiempo de compilación.

## ✅ Implementación Completada

### 📦 Clases Genéricas Creadas

#### **1. Resource<T>** - Estado genérico para operaciones asíncronas
```kotlin
sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
    data class Loading<out T>(val data: T? = null) : Resource<T>()
    object Initial : Resource<Nothing>()
}
```

**Ventajas:**
- ✅ Type-safe: `Resource<List<User>>`, `Resource<String>`, etc.
- ✅ Reutilizable para cualquier tipo de datos
- ✅ Pattern matching con `when`
- ✅ Manejo centralizado de estados

**Ejemplos de uso:**
```kotlin
val userResource: Resource<User> = Resource.Success(user)
val usersResource: Resource<List<User>> = Resource.Loading()
val stringResource: Resource<String> = Resource.Error("Error")
```

#### **2. UiState<T>** - Estado simplificado de UI
```kotlin
sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
    object Empty : UiState<Nothing>()
}
```

#### **3. BaseViewModel<T>** - ViewModel genérico base
```kotlin
abstract class BaseViewModel<T> : ViewModel() {
    protected val _state = MutableStateFlow<Resource<T>>(Resource.Initial)
    val state: StateFlow<Resource<T>> = _state.asStateFlow()
    
    protected fun executeWithResource(block: suspend () -> T) {
        // Manejo automático de estados
    }
}
```

**Ventajas:**
- ✅ Elimina código repetitivo
- ✅ Manejo de errores centralizado
- ✅ Funciones helper reutilizables
- ✅ Type-safe para el tipo de datos que maneja

#### **4. BaseRepository** - Repository base con helpers
```kotlin
abstract class BaseRepository {
    protected suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Result<T> {
        // Manejo de errores automático
    }
}
```

## 🏗️ Estructura Creada

```
core/
├── common/
│   ├── Resource.kt          ✅ Resource<T> genérico
│   └── UiState.kt           ✅ UiState<T> genérico
├── base/
│   ├── BaseViewModel.kt     ✅ ViewModel<T> base
│   └── BaseRepository.kt    ✅ Repository base
└── utils/
    └── GenericTypes.kt      ✅ Tipos genéricos útiles

ui/
├── viewmodel/
│   └── GenericApiViewModel.kt  ✅ Ejemplo usando BaseViewModel
└── screens/
    └── GenericDemoScreen.kt    ✅ UI con pattern matching
```

## 🎯 Patrones de Generics Implementados

### 1. **Sealed Classes Genéricas**
```kotlin
sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    // ...
}

// Uso:
Resource<List<User>>
Resource<String>
Resource<Int>
```

### 2. **Funciones Genéricas**
```kotlin
// Función genérica que funciona con cualquier tipo T
inline fun <T, R> Resource<T>.map(transform: (T) -> R): Resource<R>

// Uso:
val userNames: Resource<List<String>> = 
    userResource.map { users -> users.map { it.name } }
```

### 3. **Extension Functions Genéricas**
```kotlin
val <T> Resource<T>.isSuccess: Boolean
    get() = this is Resource.Success

// Uso con cualquier tipo:
if (Resource<User>.isSuccess) { ... }
if (Resource<List<Post>>.isSuccess) { ... }
```

### 4. **Clases con Múltiples Parámetros Genéricos**
```kotlin
sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()
}

// Uso:
Either<Error, User>  // Left = Error, Right = User
Either<String, Int>   // Left = String, Right = Int
```

### 5. **Constrains de Tipos**
```kotlin
// T debe ser un subtipo de Comparable
fun <T : Comparable<T>> max(a: T, b: T): T {
    return if (a > b) a else b
}

// Múltiples constraints
fun <T> process(item: T) where T : Comparable<T>, T : Serializable {
    // T debe implementar ambas interfaces
}
```

## 📱 VIEWMODELS - Gestión de Estado UI

### Arquitectura de ViewModels

```
┌─────────────────────────────────────┐
│        UI (Composable)              │
│  - Observa StateFlow                │
│  - Recompone automáticamente        │
└─────────────┬───────────────────────┘
              │ observa
              ↓
┌─────────────────────────────────────┐
│         ViewModel                   │
│  - StateFlow<Resource<T>>           │
│  - Maneja lógica de negocio         │
│  - Sobrevive a cambios de config    │
└─────────────┬───────────────────────┘
              │ llama
              ↓
┌─────────────────────────────────────┐
│         Repository                  │
│  - Operaciones de datos             │
│  - Combina fuentes de datos         │
└─────────────────────────────────────┘
```

### Características Implementadas

#### **1. Manejo de Estados**
```kotlin
@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: MyRepository
) : BaseViewModel<List<Item>>() {
    
    fun loadData() {
        executeWithResource {
            repository.getData().getOrThrow()
        }
    }
}
```

**Estados automáticos:**
- ✅ Initial → Loading → Success/Error
- ✅ Manejo de errores centralizado
- ✅ Reintentos fáciles

#### **2. StateFlow vs LiveData**

**StateFlow (recomendado):**
```kotlin
private val _state = MutableStateFlow<Resource<T>>(Resource.Initial)
val state: StateFlow<Resource<T>> = _state.asStateFlow()

// En Compose:
val state by viewModel.state.collectAsState()
```

**Ventajas:**
- ✅ Integración nativa con Compose
- ✅ Operadores de Flow (map, filter, etc.)
- ✅ Soporte para coroutines
- ✅ Thread-safe

#### **3. ViewModelScope**
```kotlin
fun loadData() {
    viewModelScope.launch {
        // Se cancela automáticamente cuando el ViewModel se destruye
        val result = repository.getData()
        _state.value = Resource.Success(result)
    }
}
```

#### **4. Exception Handling**
```kotlin
private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    handleError(throwable)
}

viewModelScope.launch(exceptionHandler) {
    // Errores capturados automáticamente
}
```

## 🎨 Ejemplos Prácticos

### Ejemplo 1: ViewModel Genérico Simple
```kotlin
@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : BaseViewModel<List<Product>>() {
    
    fun loadProducts() {
        executeWithResource {
            repository.getProducts().getOrThrow()
        }
    }
    
    fun searchProducts(query: String) {
        executeWithResource {
            repository.searchProducts(query).getOrThrow()
        }
    }
}
```

### Ejemplo 2: UI con Pattern Matching
```kotlin
@Composable
fun ProductScreen(viewModel: ProductViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    
    when (state) {
        is Resource.Initial -> InitialState()
        is Resource.Loading -> LoadingState()
        is Resource.Success -> ProductList(state.data)
        is Resource.Error -> ErrorState(state.message)
    }
}
```

### Ejemplo 3: Transformar Resource<T>
```kotlin
// De Resource<List<User>> a Resource<List<String>>
val userNames: Resource<List<String>> = userResource.map { users ->
    users.map { it.name }
}

// Obtener datos o valor por defecto
val users = userResource.getDataOrNull() ?: emptyList()
```

### Ejemplo 4: Tipos Genéricos Complejos
```kotlin
// PagedList genérico
data class PagedList<T>(
    val items: List<T>,
    val page: Int,
    val hasMore: Boolean
)

// Uso:
val pagedUsers: Resource<PagedList<User>>
val pagedPosts: Resource<PagedList<Post>>
```

### Ejemplo 5: Either para Success/Error
```kotlin
sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()
}

// Uso típico: Either<Error, Data>
fun loadUser(): Either<ErrorType, User> {
    return try {
        Either.Right(fetchUser())
    } catch (e: Exception) {
        Either.Left(ErrorType.NetworkError)
    }
}
```

## 💡 Ventajas de los Generics

### 1. **Reutilización de Código**
```kotlin
// Un Resource<T> sirve para TODO
Resource<User>
Resource<List<Post>>
Resource<String>
Resource<Int>
// etc...
```

### 2. **Type Safety**
```kotlin
// El compilador te protege de errores de tipos
val userResource: Resource<User> = Resource.Success(user)
val user: User = userResource.data  // ✅ Type-safe

// Esto NO compila:
val wrongType: String = userResource.data  // ❌ Error de compilación
```

### 3. **Código más Limpio**
```kotlin
// Sin generics (código repetitivo):
class UserResource { ... }
class PostResource { ... }
class CommentResource { ... }

// Con generics (un solo código):
class Resource<T> { ... }
```

### 4. **Mejor Mantenibilidad**
- Cambios en un solo lugar
- Menos código = menos bugs
- API consistente

## 🎓 Mejores Prácticas

### ViewModels:
✅ **Hacer:**
- Usar StateFlow para estados
- Heredar de BaseViewModel para código común
- Separar lógica de negocio de la UI
- Usar viewModelScope para coroutines
- Manejar errores centralizadamente

❌ **Evitar:**
- Mantener referencias a Context
- Hacer operaciones de UI
- Usar LiveData en proyectos nuevos
- Mezclar lógica de datos con UI

### Generics:
✅ **Hacer:**
- Usar `out` para covarianza cuando sea apropiado
- Crear tipos genéricos reutilizables
- Usar constrains cuando sea necesario
- Nombrar parámetros genéricos descriptivamente

❌ **Evitar:**
- Generics innecesariamente complejos
- Demasiados parámetros genéricos
- Ignorar varianza (in/out)

## 🚀 Cómo Usar

### 1. Crear un ViewModel
```kotlin
@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: MyRepository
) : BaseViewModel<MyDataType>() {
    
    fun loadData() {
        executeWithResource {
            repository.getData().getOrThrow()
        }
    }
}
```

### 2. Usar en UI
```kotlin
@Composable
fun MyScreen(viewModel: MyViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    
    when (state) {
        is Resource.Success -> ShowData(state.data)
        is Resource.Error -> ShowError(state.message)
        is Resource.Loading -> ShowLoading()
        is Resource.Initial -> ShowInitial()
    }
}
```

## 📊 Comparación de Enfoques

| Aspecto | Sin Generics | Con Generics |
|---------|--------------|--------------|
| Código | Repetitivo | Reutilizable |
| Type Safety | Manual | Automática |
| Mantenimiento | Difícil | Fácil |
| Testing | Más tests | Menos tests |
| API | Inconsistente | Consistente |

## 🔗 Archivos de Referencia

- `Resource.kt` - Resource<T> genérico
- `UiState.kt` - UiState<T> genérico  
- `BaseViewModel.kt` - ViewModel base
- `GenericApiViewModel.kt` - Ejemplo de uso
- `GenericDemoScreen.kt` - UI con pattern matching
- `GenericTypes.kt` - Más tipos genéricos útiles

¡Generics y ViewModels completamente integrados! 🎉

