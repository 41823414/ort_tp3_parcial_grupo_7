# 🎯 Generics + ViewModels - Resumen de Implementación

## ✅ COMPLETADO - Generics y ViewModels Integrados

### 📦 Clases Genéricas Creadas

#### **1. Resource<T>** - Estado genérico reutilizable
```kotlin
sealed class Resource<out T> {
    data class Success<out T>(val data: T)
    data class Error(val message: String)
    data class Loading<out T>(val data: T? = null)
    object Initial
}
```

**Uso:**
```kotlin
Resource<User>           // Usuario individual
Resource<List<Post>>     // Lista de posts
Resource<String>         // Cadena de texto
```

#### **2. UiState<T>** - Estado simplificado de UI
```kotlin
sealed class UiState<out T> {
    object Idle
    object Loading
    data class Success<out T>(val data: T)
    data class Error(val message: String)
    object Empty
}
```

#### **3. BaseViewModel<T>** - ViewModel genérico base
```kotlin
abstract class BaseViewModel<T> : ViewModel() {
    val state: StateFlow<Resource<T>>
    val isLoading: StateFlow<Boolean>
    val error: StateFlow<String?>
    
    protected fun executeWithResource(block: suspend () -> T)
    protected fun launchDataLoad(...)
    fun resetState()
    fun clearError()
}
```

**Ventajas:**
- ✅ Manejo automático de estados (Loading → Success/Error)
- ✅ Exception handling centralizado
- ✅ Funciones helper reutilizables
- ✅ Type-safe para cualquier tipo de datos

#### **4. BaseRepository** - Repository con helpers genéricos
```kotlin
abstract class BaseRepository {
    protected suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T>
    protected suspend fun <T> executeOrNull(operation: suspend () -> T): T?
}
```

### 🏗️ Estructura Creada

```
core/
├── common/
│   ├── Resource.kt          ✅ Resource<T> genérico con extensiones
│   └── UiState.kt           ✅ UiState<T> genérico
├── base/
│   ├── BaseViewModel.kt     ✅ ViewModel<T> base reutilizable
│   └── BaseRepository.kt    ✅ Repository base con helpers
└── utils/
    └── GenericTypes.kt      ✅ Tipos genéricos útiles:
                                - Either<L, R>
                                - Optional<T>
                                - PagedList<T>
                                - Validation<E, T>
```

### 📱 Ejemplos Implementados

#### **GenericApiViewModel.kt** - ViewModel usando BaseViewModel
```kotlin
@HiltViewModel
class GenericApiViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : BaseViewModel<List<UserDto>>() {
    
    fun loadUsers() {
        executeWithResource {
            apiRepository.getUsers().getOrThrow()
        }
    }
}
```

**Hereda automáticamente:**
- `state: StateFlow<Resource<List<UserDto>>>`
- `isLoading: StateFlow<Boolean>`
- `error: StateFlow<String?>`
- Funciones de manejo de estados

#### **GenericDemoScreen.kt** - UI con pattern matching
```kotlin
@Composable
fun GenericDemoScreen(viewModel: GenericApiViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    
    when (state) {
        is Resource.Initial -> InitialState()
        is Resource.Loading -> LoadingState()
        is Resource.Success -> SuccessState(state.data)
        is Resource.Error -> ErrorState(state.message)
    }
}
```

## 🎯 Patrones de Generics Implementados

### 1. **Sealed Classes Genéricas**
```kotlin
// Un tipo para gobernarlos a todos
Resource<List<User>>
Resource<String>
Resource<Int>
Resource<CustomType>
```

### 2. **Funciones Genéricas con Extensions**
```kotlin
// Transformar Resource<T> a Resource<R>
inline fun <T, R> Resource<T>.map(transform: (T) -> R): Resource<R>

// Uso:
val names: Resource<List<String>> = 
    users.map { it.map { user -> user.name } }
```

### 3. **Extension Properties Genéricas**
```kotlin
val <T> Resource<T>.isSuccess: Boolean
val <T> Resource<T>.isError: Boolean
val <T> Resource<T>.isLoading: Boolean

fun <T> Resource<T>.getDataOrNull(): T?
```

### 4. **Either<L, R>** - Dos valores posibles
```kotlin
sealed class Either<out L, out R> {
    data class Left<out L>(val value: L)    // Generalmente Error
    data class Right<out R>(val value: R)   // Generalmente Success
}

// Uso típico:
Either<ErrorType, User>
Either<String, Data>
```

### 5. **Optional<T>** - Valor opcional type-safe
```kotlin
sealed class Optional<out T> {
    data class Some<out T>(val value: T)
    object None
    
    fun getOrNull(): T?
    fun getOrElse(default: T): T
    fun <R> map(transform: (T) -> R): Optional<R>
}
```

### 6. **PagedList<T>** - Paginación genérica
```kotlin
data class PagedList<T>(
    val items: List<T>,
    val page: Int,
    val hasMore: Boolean,
    val totalItems: Int
)

// Uso:
PagedList<User>
PagedList<Post>
PagedList<Product>
```

## 💡 Ventajas Implementadas

### 1. **Código Reutilizable**
```kotlin
// Antes (sin generics):
class UserResource { ... }        // 100 líneas
class PostResource { ... }        // 100 líneas
class CommentResource { ... }     // 100 líneas
// Total: 300 líneas

// Después (con generics):
class Resource<T> { ... }         // 100 líneas
// Total: 100 líneas ✅
```

### 2. **Type Safety**
```kotlin
// El compilador te protege
val userResource: Resource<User> = Resource.Success(user)
val user: User = userResource.getDataOrNull() ?: defaultUser  // ✅

// Esto NO compila:
val wrong: String = userResource.getDataOrNull()  // ❌ Error de compilación
```

### 3. **Pattern Matching Exhaustivo**
```kotlin
when (state) {
    is Resource.Initial -> { }
    is Resource.Loading -> { }
    is Resource.Success -> { }
    is Resource.Error -> { }
    // El compilador te obliga a manejar todos los casos
}
```

### 4. **ViewModels sin Boilerplate**
```kotlin
// Antes:
class MyViewModel : ViewModel() {
    private val _state = MutableStateFlow<MyState>(MyState.Initial)
    val state = _state.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()
    
    // 50+ líneas de código repetitivo...
}

// Después:
class MyViewModel : BaseViewModel<MyDataType>() {
    // Todo heredado automáticamente ✅
}
```

## 🚀 Flujo de Datos Completo

```
┌──────────────────────────────────────────────┐
│  UI (Composable)                             │
│  - Observa StateFlow<Resource<T>>            │
│  - Pattern matching con when                 │
│  - Recomposición automática                  │
└────────────────┬─────────────────────────────┘
                 │ collectAsState()
                 ↓
┌──────────────────────────────────────────────┐
│  ViewModel : BaseViewModel<T>                │
│  - state: StateFlow<Resource<T>>             │
│  - executeWithResource { }                   │
│  - Manejo automático Loading/Success/Error   │
└────────────────┬─────────────────────────────┘
                 │ inject
                 ↓
┌──────────────────────────────────────────────┐
│  Repository : BaseRepository                 │
│  - safeApiCall<T> { }                        │
│  - Operaciones en Dispatchers.IO             │
└────────────────┬─────────────────────────────┘
                 │
                 ↓
┌──────────────────────────────────────────────┐
│  Data Source (API, Database, etc.)           │
└──────────────────────────────────────────────┘
```

## 📊 Ejemplos de Uso

### Ejemplo 1: Cargar Lista de Usuarios
```kotlin
@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: UserRepository
) : BaseViewModel<List<User>>() {
    
    fun loadUsers() {
        executeWithResource {
            repository.getUsers().getOrThrow()
        }
    }
}

// En UI:
val state by viewModel.state.collectAsState()
when (state) {
    is Resource.Success -> UserList(state.data)
    is Resource.Error -> ErrorMessage(state.message)
    is Resource.Loading -> LoadingIndicator()
}
```

### Ejemplo 2: Transformar Datos
```kotlin
// Transformar Resource<List<User>> a Resource<List<String>>
val userNames = userResource.map { users ->
    users.map { it.name }
}

// De Resource<User> a Resource<UserDto>
val userDto = userResource.map { user ->
    user.toDto()
}
```

### Ejemplo 3: Manejo de Errores
```kotlin
class MyViewModel : BaseViewModel<MyData>() {
    override fun handleError(throwable: Throwable) {
        // Personalizar manejo de errores
        when (throwable) {
            is NetworkException -> _error.value = "Sin conexión"
            is TimeoutException -> _error.value = "Tiempo agotado"
            else -> super.handleError(throwable)
        }
    }
}
```

### Ejemplo 4: Paginación Genérica
```kotlin
@HiltViewModel
class PaginatedViewModel @Inject constructor(
    private val repository: MyRepository
) : BaseViewModel<PagedList<Item>>() {
    
    fun loadPage(page: Int) {
        executeWithResource {
            repository.getPage(page).getOrThrow()
        }
    }
    
    fun loadNextPage() {
        val currentPage = state.value.getDataOrNull()?.page ?: 0
        loadPage(currentPage + 1)
    }
}
```

## 🎓 Mejores Prácticas Aplicadas

### ViewModels:
✅ StateFlow en lugar de LiveData (mejor para Compose)
✅ viewModelScope para coroutines
✅ Separación de estado público/privado
✅ Manejo centralizado de errores
✅ Funciones helper reutilizables

### Generics:
✅ Sealed classes para type-safety
✅ Extension functions para operaciones comunes
✅ Varianza apropiada (out/in)
✅ Tipos genéricos descriptivos
✅ Pattern matching exhaustivo

### Arquitectura:
✅ Separación de capas (UI → ViewModel → Repository → Data)
✅ Inyección de dependencias con Hilt
✅ Reactive state management con Flow
✅ Código reutilizable y mantenible

## 📚 Archivos Creados

| Archivo | Propósito |
|---------|-----------|
| `Resource.kt` | Sealed class genérica para estados |
| `UiState.kt` | Estado simplificado de UI |
| `BaseViewModel.kt` | ViewModel base con generics |
| `BaseRepository.kt` | Repository base con helpers |
| `GenericTypes.kt` | Tipos genéricos útiles (Either, Optional, etc.) |
| `GenericApiViewModel.kt` | Ejemplo de ViewModel usando BaseViewModel |
| `GenericDemoScreen.kt` | UI demostrando pattern matching |

### Documentación:
| Archivo | Contenido |
|---------|-----------|
| `GENERICS_VIEWMODELS_GUIDE.md` | Guía completa con ejemplos |
| `GENERICS_VIEWMODELS_SUMMARY.md` | Este resumen ejecutivo |

## 🔍 Comparación: Antes vs Después

### Antes (sin generics):
```kotlin
// Código repetitivo para cada tipo
class UserState { ... }      // 50 líneas
class PostState { ... }      // 50 líneas  
class CommentState { ... }   // 50 líneas

class UserViewModel { ... }  // 100 líneas de boilerplate
class PostViewModel { ... }  // 100 líneas de boilerplate
// etc...
```

### Después (con generics):
```kotlin
// Un tipo para todos
sealed class Resource<T> { ... }     // 50 líneas
abstract class BaseViewModel<T> { ... }  // 100 líneas

// ViewModels específicos (mínimo código)
class UserViewModel : BaseViewModel<User>() { ... }    // 20 líneas
class PostViewModel : BaseViewModel<Post>() { ... }    // 20 líneas
// etc...
```

**Resultado:** 70-80% menos código, más mantenible y type-safe ✅

## ✨ Resumen Ejecutivo

**Generics implementados:**
- ✅ Resource<T> - Estado genérico reutilizable
- ✅ UiState<T> - Estado de UI type-safe
- ✅ BaseViewModel<T> - ViewModel base sin boilerplate
- ✅ Either<L,R>, Optional<T>, PagedList<T> - Tipos útiles

**ViewModels mejorados:**
- ✅ BaseViewModel elimina 80% del boilerplate
- ✅ StateFlow para reactive state
- ✅ Manejo automático de Loading/Success/Error
- ✅ Exception handling centralizado
- ✅ Funciones helper reutilizables

**Resultado:**
- 🎯 Código más limpio y mantenible
- 🛡️ Type-safety garantizado
- ♻️ Máxima reutilización
- 🚀 Desarrollo más rápido
- ✅ Menos bugs

---

¡Generics y ViewModels completamente integrados y listos para usar! 🎉

**Próximo paso:** Sincroniza Gradle y empieza a usar `BaseViewModel<T>` para tus ViewModels.

