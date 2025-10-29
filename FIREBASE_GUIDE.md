# 🔥 FIREBASE - Guía de Implementación

## ✅ Configuración Completada

### 1. **Dependencias Agregadas**
- Firebase BoM (Bill of Materials) - Gestión centralizada de versiones
- Firebase Analytics
- Firebase Authentication
- Firebase Firestore
- Firebase Storage
- Firebase Messaging
- Firebase Crashlytics
- Google Services Plugin

### 2. **Archivos Modificados**

#### `gradle/libs.versions.toml`
```toml
firebaseBom = "33.7.0"
googleServices = "4.4.2"
```

#### `build.gradle.kts` (Raíz)
```kotlin
alias(libs.plugins.google.services) apply false
```

#### `app/build.gradle.kts`
```kotlin
alias(libs.plugins.google.services)
```

### 3. **Módulos Creados**

#### `FirebaseConfig.kt`
Configuración centralizada de Firebase con acceso a todos los servicios.

#### `FirebaseAuthManager.kt`
Gestión completa de autenticación:
- ✅ Registro con email/contraseña
- ✅ Login con email/contraseña
- ✅ Cerrar sesión
- ✅ Enviar email de verificación
- ✅ Recuperación de contraseña
- ✅ Actualizar contraseña
- ✅ Eliminar cuenta

#### `FirebaseFirestoreManager.kt`
Gestión completa de Firestore:
- ✅ Crear/Actualizar documentos
- ✅ Obtener documentos
- ✅ Obtener colecciones
- ✅ Queries con filtros
- ✅ Eliminar documentos
- ✅ Operaciones batch

#### `FirebaseModule.kt`
Módulo de Hilt para inyección de dependencias de Firebase.

---

## 📋 IMPORTANTE: Configuración del Package Name

⚠️ **PROBLEMA DETECTADO**: Tu archivo `google-services.json` está configurado para el package:
```
com.ar.edu.ort.parcial
```

Pero tu aplicación usa el package:
```
ort.argentina.yatay.tp3.tp3_parcial_grupal3
```

### ✅ Solución:
Debes ir a la **Firebase Console** y:
1. Agregar una nueva aplicación Android
2. Usar el package name: `ort.argentina.yatay.tp3.tp3_parcial_grupal3`
3. Descargar el nuevo `google-services.json`
4. Reemplazar el archivo actual

**O alternativamente**, cambiar el `applicationId` en `app/build.gradle.kts`:
```kotlin
applicationId = "com.ar.edu.ort.parcial"
```

---

## 🔐 Seguridad

✅ **El archivo `google-services.json` ya está protegido en `.gitignore`**

Contenido agregado:
```
# Firebase
google-services.json
**/google-services.json
```

**Nota importante**: Este archivo contiene información sensible como:
- API Keys
- Project ID
- Storage bucket
- Client IDs

**NUNCA** lo subas a un repositorio público.

---

## 🚀 Uso Básico

### 1. Inyectar Firebase en tu ViewModel

```kotlin
@HiltViewModel
class MyViewModel @Inject constructor(
    private val authManager: FirebaseAuthManager,
    private val firestoreManager: FirebaseFirestoreManager
) : ViewModel() {
    
    // Registro de usuario
    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            val result = authManager.signUpWithEmail(email, password)
            result.onSuccess { user ->
                // Usuario registrado exitosamente
            }.onFailure { error ->
                // Manejar error
            }
        }
    }
    
    // Login
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            val result = authManager.signInWithEmail(email, password)
            // ...
        }
    }
    
    // Guardar datos en Firestore
    fun saveUserData(userId: String, userData: UserData) {
        viewModelScope.launch {
            val result = firestoreManager.setDocument(
                collection = "users",
                documentId = userId,
                data = userData
            )
            // ...
        }
    }
    
    // Obtener datos de Firestore
    fun getUserData(userId: String) {
        viewModelScope.launch {
            val result = firestoreManager.getDocument(
                collection = "users",
                documentId = userId,
                clazz = UserData::class.java
            )
            // ...
        }
    }
}
```

### 2. Verificar Usuario Autenticado

```kotlin
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authManager: FirebaseAuthManager
) : ViewModel() {
    
    val isLoggedIn: Boolean
        get() = authManager.isUserLoggedIn
    
    val currentUser
        get() = authManager.currentUser
}
```

### 3. Operaciones con Firestore

```kotlin
// Crear un documento
firestoreManager.setDocument(
    collection = "productos",
    documentId = "producto123",
    data = Producto(nombre = "Laptop", precio = 1000.0)
)

// Obtener colección completa
val productos = firestoreManager.getCollection(
    collection = "productos",
    clazz = Producto::class.java
)

// Query con filtro
val productosBaratos = firestoreManager.getCollectionWhere(
    collection = "productos",
    field = "precio",
    value = 500.0,
    clazz = Producto::class.java
)

// Actualizar campos
firestoreManager.updateDocument(
    collection = "productos",
    documentId = "producto123",
    updates = mapOf("precio" to 900.0)
)

// Eliminar documento
firestoreManager.deleteDocument(
    collection = "productos",
    documentId = "producto123"
)
```

---

## 📊 Estructura de Datos en Firestore

### Ejemplo de modelo de datos:

```kotlin
data class UserData(
    val id: String = "",
    val email: String = "",
    val displayName: String = "",
    val photoUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
```

**Nota**: Los modelos deben tener un constructor sin argumentos para que Firestore pueda deserializarlos.

---

## 🔧 Próximos Pasos

1. ✅ Corregir el package name en Firebase Console
2. ✅ Descargar el `google-services.json` correcto
3. ✅ Sync Project with Gradle Files
4. ✅ Probar la autenticación
5. ✅ Crear las reglas de seguridad en Firestore
6. ✅ Implementar las pantallas de login/registro

---

## 📚 Recursos Adicionales

- [Firebase Documentation](https://firebase.google.com/docs)
- [Firebase Authentication](https://firebase.google.com/docs/auth)
- [Cloud Firestore](https://firebase.google.com/docs/firestore)
- [Firebase Security Rules](https://firebase.google.com/docs/rules)

---

## 🎯 Resumen

✅ **Completado**:
- Firebase integrado con Hilt
- Managers para Auth y Firestore
- Configuración de seguridad (.gitignore)
- Estructura base lista para usar

⚠️ **Pendiente**:
- Ajustar package name en Firebase Console
- Descargar google-services.json correcto
- Implementar UI de autenticación

