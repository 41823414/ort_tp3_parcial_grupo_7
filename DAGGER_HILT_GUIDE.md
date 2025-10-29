# Guía de Dagger/Hilt - Dependency Injection

## 📚 ¿Qué es Dagger/Hilt?

**Dagger** es un framework de inyección de dependencias para Java/Kotlin desarrollado por Google.
**Hilt** es una capa construida sobre Dagger que simplifica su uso en Android.

## ✅ Configuración Completada

### Dependencias agregadas:
- `hilt-android` (2.51.1)
- `hilt-compiler` (2.51.1)
- `hilt-navigation-compose` (1.2.0)
- `kotlin-kapt` plugin

## 🎯 Componentes Principales

### 1. @HiltAndroidApp
```kotlin
@HiltAndroidApp
class MyApplication : Application()
```
- Inicializa Hilt en la aplicación
- Ya configurado en `MyApplication.kt`

### 2. @AndroidEntryPoint
```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity()
```
- Habilita inyección de dependencias en Activities, Fragments, Views, etc.
- Ya configurado en `MainActivity.kt`

### 3. @Inject
```kotlin
class ExampleRepository @Inject constructor(
    // dependencias aquí
)
```
- Indica a Hilt cómo crear una instancia de la clase

### 4. @HiltViewModel
```kotlin
@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val repository: ExampleRepository
) : ViewModel()
```
- Permite inyectar ViewModels en Composables con `hiltViewModel()`

## 🔧 Módulos de Dagger/Hilt

### @Module y @InstallIn
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(): AppDatabase { ... }
}
```

### @Provides vs @Binds

**@Provides**: Para crear instancias cuando necesitas lógica personalizada
```kotlin
@Provides
fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.com")
        .build()
}
```

**@Binds**: Para vincular interfaces a implementaciones (más eficiente)
```kotlin
@Binds
abstract fun bindRepository(
    impl: ExampleRepository
): ExampleRepositoryInterface
```

## 📦 Scopes en Hilt

| Scope | Componente | Ciclo de vida |
|-------|-----------|---------------|
| @Singleton | SingletonComponent | Toda la aplicación |
| @ActivityScoped | ActivityComponent | Durante la Activity |
| @ViewModelScoped | ViewModelComponent | Durante el ViewModel |
| @FragmentScoped | FragmentComponent | Durante el Fragment |
| @ServiceScoped | ServiceComponent | Durante el Service |

## 🏷️ Qualifiers

Distinguen entre múltiples implementaciones del mismo tipo:

```kotlin
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Provides
@IoDispatcher
fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

// Uso:
class Repository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
)
```

## 📁 Estructura del Proyecto

```
app/src/main/java/.../
├── di/
│   ├── AppModule.kt              # Módulo principal (@Provides)
│   ├── RepositoryModule.kt       # Módulo con @Binds
│   ├── Qualifiers.kt             # Qualifiers personalizados
│   └── ScopesExampleModule.kt    # Ejemplos de scopes
├── data/
│   ├── repository/
│   │   ├── ExampleRepository.kt
│   │   └── ExampleRepositoryInterface.kt
│   └── local/
│       └── PreferencesManager.kt
├── ui/
│   ├── viewmodel/
│   │   └── ExampleViewModel.kt
│   └── screens/
│       └── ExampleScreen.kt
├── MyApplication.kt              # @HiltAndroidApp
└── MainActivity.kt               # @AndroidEntryPoint
```

## 🚀 Uso en Compose

```kotlin
@Composable
fun MyScreen(
    viewModel: MyViewModel = hiltViewModel()
) {
    // El ViewModel ya tiene todas sus dependencias inyectadas
}
```

## 💡 Ejemplos Incluidos

1. **ExampleRepository**: Repository con inyección de dependencias
2. **ExampleViewModel**: ViewModel con Hilt
3. **PreferencesManager**: Inyección de Context con @ApplicationContext
4. **AppModule**: Providers de Dispatchers y configuración
5. **RepositoryModule**: Uso de @Binds
6. **ExampleScreen**: Composable usando hiltViewModel()

## 🔍 Ventajas de Dagger/Hilt

✅ **Desacoplamiento**: Clases no dependen de implementaciones concretas
✅ **Testeable**: Fácil reemplazar dependencias en tests
✅ **Escalable**: Manejo automático de dependencias complejas
✅ **Rendimiento**: Inyección en tiempo de compilación
✅ **Seguridad**: Errores detectados en compilación, no en runtime

## 📖 Próximos Pasos

Para usar Hilt/Dagger en tu proyecto:

1. **Sincroniza Gradle** en Android Studio
2. **Build el proyecto** para generar código de Hilt
3. **Usa `hiltViewModel()`** en tus Composables
4. **Crea módulos** en el paquete `di/` según necesites

## 🔗 Recursos

- [Documentación oficial de Hilt](https://dagger.dev/hilt/)
- [Codelabs de Hilt](https://developer.android.com/codelabs/android-hilt)
- [Guía de arquitectura Android](https://developer.android.com/topic/architecture)

