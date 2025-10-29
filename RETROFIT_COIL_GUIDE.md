# 📡 Retrofit + 🖼️ Coil - Guía Completa

## 📚 ¿Qué son Retrofit y Coil?

### **Retrofit** - Cliente HTTP Type-Safe
Retrofit es la librería estándar de facto para hacer llamadas HTTP en Android. Convierte tu API HTTP en una interfaz Java/Kotlin.

### **Coil** - Carga de Imágenes para Compose
Coil (Coroutine Image Loader) es una librería moderna de carga de imágenes optimizada para Kotlin y Jetpack Compose.

## ✅ Configuración Completada

### Dependencias agregadas:

#### **Retrofit Stack:**
- `retrofit` (2.11.0) - Cliente HTTP
- `retrofit-converter-gson` (2.11.0) - Convertidor JSON
- `okhttp` (4.12.0) - Cliente HTTP subyacente
- `okhttp-logging-interceptor` (4.12.0) - Logging de peticiones
- `gson` (2.11.0) - Parser JSON

#### **Coil:**
- `coil-compose` (2.7.0) - Integración con Compose
  - Incluye networking integrado con OkHttp
  - No necesita dependencias adicionales

### Permisos agregados:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

## 🌐 RETROFIT - Networking/API REST

### 🏗️ Arquitectura Implementada

```
API REST (JSONPlaceholder)
    ↓
ApiService (interfaz con endpoints)
    ↓
Retrofit (cliente HTTP)
    ↓
OkHttp (networking)
    ↓
ApiRepository (lógica de negocio)
    ↓
ApiViewModel (manejo de estado)
    ↓
ApiUsersScreen (UI)
```

### 📁 Estructura Creada

#### **1. ApiService.kt** - Definición de Endpoints
```kotlin
interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<UserDto>>
    
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") userId: Int): Response<UserDto>
    
    @POST("posts")
    suspend fun createPost(@Body post: PostDto): Response<PostDto>
    
    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") postId: Int): Response<Unit>
}
```

**Anotaciones HTTP:**
- `@GET` - Petición GET
- `@POST` - Petición POST
- `@PUT` - Petición PUT
- `@DELETE` - Petición DELETE
- `@Path` - Parámetro en la URL
- `@Query` - Query parameter (?key=value)
- `@Body` - Cuerpo de la petición

#### **2. DTOs (Data Transfer Objects)**
- `UserDto.kt` - Modelo de usuario
- `PostDto.kt` - Modelo de publicación

**Anotaciones Gson:**
```kotlin
@SerializedName("id") // Mapea el campo JSON
val id: Int
```

#### **3. NetworkModule.kt** - Configuración con Hilt
Provee todas las dependencias de networking:

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
```

**Componentes provistos:**
- ✅ `Gson` - Parser JSON configurado
- ✅ `HttpLoggingInterceptor` - Logs de peticiones
- ✅ `OkHttpClient` - Cliente HTTP con timeouts
- ✅ `Retrofit` - Cliente API configurado
- ✅ `ApiService` - Interfaz de endpoints

#### **4. ApiRepository.kt** - Capa de datos
Maneja las llamadas a la API y el manejo de errores:

```kotlin
suspend fun getUsers(): Result<List<UserDto>> = withContext(Dispatchers.IO) {
    try {
        val response = apiService.getUsers()
        if (response.isSuccessful && response.body() != null) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Exception("Error: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}
```

**Características:**
- ✅ Ejecuta en `Dispatchers.IO`
- ✅ Manejo de errores con `Result<T>`
- ✅ Validación de respuestas
- ✅ Inyectado con Hilt

#### **5. ApiViewModel.kt** - Lógica de presentación
```kotlin
@HiltViewModel
class ApiViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {
    
    fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            
            apiRepository.getUsers()
                .onSuccess { users -> _users.value = users }
                .onFailure { error -> _uiState.value = UiState.Error(...) }
        }
    }
}
```

## 🖼️ COIL - Carga de Imágenes

### 🎯 Uso en Compose

#### Carga básica de imagen:
```kotlin
AsyncImage(
    model = "https://example.com/image.jpg",
    contentDescription = "Descripción",
    modifier = Modifier.size(100.dp)
)
```

#### Con configuraciones avanzadas:
```kotlin
AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
        .data("https://example.com/image.jpg")
        .crossfade(true)
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.error)
        .build(),
    contentDescription = "Avatar",
    modifier = Modifier
        .size(60.dp)
        .clip(CircleShape),
    contentScale = ContentScale.Crop
)
```

### ✨ Características de Coil

#### **1. Optimizaciones automáticas:**
- ✅ Caché en memoria
- ✅ Caché en disco
- ✅ Downsampling automático
- ✅ Cancelación automática

#### **2. Integraciones:**
- ✅ OkHttp (ya integrado en tu proyecto)
- ✅ Coroutines
- ✅ Jetpack Compose

#### **3. Transformaciones:**
```kotlin
AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .transformations(
            CircleCropTransformation(),
            BlurTransformation(),
            GrayscaleTransformation()
        )
        .build(),
    contentDescription = null
)
```

## 🎨 Pantalla de Ejemplo: ApiUsersScreen

### Características implementadas:

1. **Carga de usuarios desde API REST**
   - Usa Retrofit para obtener datos de JSONPlaceholder
   - Manejo de estados (Loading, Success, Error)

2. **Muestra avatares con Coil**
   - Imágenes circulares
   - Carga asíncrona
   - ContentScale.Crop

3. **UI interactiva**
   - Botón de refrescar
   - Lista con LazyColumn
   - Cards con Material3

## 🔧 API de Ejemplo: JSONPlaceholder

Base URL: `https://jsonplaceholder.typicode.com/`

### Endpoints disponibles:

| Endpoint | Método | Descripción |
|----------|--------|-------------|
| `/users` | GET | Lista de usuarios |
| `/users/{id}` | GET | Usuario por ID |
| `/posts` | GET | Lista de posts |
| `/posts?userId={id}` | GET | Posts de un usuario |
| `/posts` | POST | Crear post |
| `/posts/{id}` | PUT | Actualizar post |
| `/posts/{id}` | DELETE | Eliminar post |

## 💡 Ejemplos de Uso

### Retrofit - Diferentes tipos de peticiones:

#### GET con Path Parameter:
```kotlin
@GET("users/{id}")
suspend fun getUserById(@Path("id") userId: Int): Response<UserDto>
```

#### GET con Query Parameter:
```kotlin
@GET("posts")
suspend fun getPostsByUser(@Query("userId") userId: Int): Response<List<PostDto>>
```

#### POST con Body:
```kotlin
@POST("posts")
suspend fun createPost(@Body post: PostDto): Response<PostDto>
```

#### Headers personalizados:
```kotlin
@GET("users")
suspend fun getUsers(
    @Header("Authorization") token: String
): Response<List<UserDto>>
```

#### Query Map (múltiples parámetros):
```kotlin
@GET("posts")
suspend fun searchPosts(
    @QueryMap filters: Map<String, String>
): Response<List<PostDto>>
```

### Coil - Diferentes escenarios:

#### Imagen con placeholder:
```kotlin
AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .placeholder(R.drawable.loading)
        .error(R.drawable.error_image)
        .build(),
    contentDescription = null
)
```

#### Imagen con transformación circular:
```kotlin
AsyncImage(
    model = imageUrl,
    contentDescription = null,
    modifier = Modifier
        .size(80.dp)
        .clip(CircleShape),
    contentScale = ContentScale.Crop
)
```

## ⚙️ Configuración Avanzada

### OkHttp Interceptors:

#### Agregar autenticación:
```kotlin
class AuthInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}
```

### Timeouts personalizados:
```kotlin
OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .build()
```

### Logging condicional (solo en debug):
```kotlin
if (BuildConfig.DEBUG) {
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
} else {
    loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
}
```

## 🎯 Mejores Prácticas

### Retrofit:
✅ **Hacer:**
- Usar `suspend` functions con coroutines
- Manejar errores con `Result<T>`
- Validar respuestas (`isSuccessful`, `body != null`)
- Usar DTOs separados del modelo de dominio
- Ejecutar en `Dispatchers.IO`

❌ **Evitar:**
- Llamadas síncronas (bloquean el hilo)
- No manejar errores de red
- Usar modelos de UI directamente
- Exponer Retrofit en la capa de UI

### Coil:
✅ **Hacer:**
- Usar `AsyncImage` en Compose
- Configurar placeholders y errores
- Aprovechar caché automático
- Usar ContentScale apropiado

❌ **Evitar:**
- Cargar imágenes muy grandes sin redimensionar
- No manejar estados de error
- Cargar en el hilo principal (Coil ya lo maneja)

## 🚀 Flujo Completo de Datos

```
1. Usuario interactúa con UI (ApiUsersScreen)
        ↓
2. ViewModel llama al Repository
        ↓
3. Repository usa ApiService (Retrofit)
        ↓
4. Retrofit hace petición HTTP con OkHttp
        ↓
5. Gson parsea JSON a DTO
        ↓
6. Repository devuelve Result<T>
        ↓
7. ViewModel actualiza StateFlow
        ↓
8. UI recompone automáticamente
        ↓
9. Coil carga imágenes desde URLs
```

## 📊 Debugging

### Ver logs de Retrofit:
Los logs de peticiones se muestran en Logcat con tag "OkHttp":
```
D/OkHttp: --> GET https://jsonplaceholder.typicode.com/users
D/OkHttp: <-- 200 OK (123ms)
```

### Códigos de estado HTTP comunes:
- `200` - OK (exitoso)
- `201` - Created (recurso creado)
- `400` - Bad Request (petición inválida)
- `401` - Unauthorized (no autenticado)
- `404` - Not Found (recurso no encontrado)
- `500` - Internal Server Error (error del servidor)

## 🔗 Recursos

- [Retrofit Docs](https://square.github.io/retrofit/)
- [Coil Docs](https://coil-kt.github.io/coil/)
- [OkHttp](https://square.github.io/okhttp/)
- [JSONPlaceholder](https://jsonplaceholder.typicode.com/)

## ✨ Resumen de lo Implementado

### Retrofit Stack:
✅ ApiService con endpoints REST
✅ DTOs para User y Post
✅ NetworkModule con Hilt
✅ OkHttp con logging interceptor
✅ Gson para parsing JSON
✅ ApiRepository con manejo de errores
✅ ApiViewModel con states
✅ Timeouts configurados (30s)

### Coil:
✅ Integración con Compose
✅ AsyncImage para carga de imágenes
✅ Soporte para OkHttp
✅ Imágenes circulares en pantalla de ejemplo

### UI:
✅ ApiUsersScreen completa
✅ Carga de usuarios desde API
✅ Avatares con Coil
✅ Manejo de estados (Loading, Success, Error)
✅ Botón de refresh

¡Todo listo para hacer peticiones HTTP y cargar imágenes! 🎉

