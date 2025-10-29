# 🐛 GUÍA: Cómo Ver Errores de App en el Emulador/Dispositivo

## 📱 Método 1: Logcat en Android Studio (RECOMENDADO)

### Paso a Paso:

1. **Abrir Logcat**
   - En la parte inferior de Android Studio, busca la pestaña **"Logcat"**
   - Si no la ves, ve a `View` → `Tool Windows` → `Logcat`

2. **Filtrar por tu app**
   - En el dropdown de la izquierda, selecciona tu dispositivo/emulador
   - En el segundo dropdown, selecciona el package de tu app: `com.ar.edu.ort.parcial`

3. **Filtrar por errores**
   - En el tercer dropdown (nivel de log), selecciona **"Error"** o **"Warn"**
   - Esto te mostrará solo los errores y advertencias

4. **Buscar el crash**
   - Cuando la app crashea, busca líneas que digan:
     - `FATAL EXCEPTION`
     - `AndroidRuntime`
     - `Caused by:`
   
5. **Entender el error**
   ```
   FATAL EXCEPTION: main
   Process: com.ar.edu.ort.parcial, PID: 12345
   java.lang.RuntimeException: Unable to create application
   Caused by: com.google.firebase.FirebaseException: ...
   ```
   - La línea `Caused by:` te dice la causa raíz del problema

---

## 🔍 Método 2: Terminal ADB (Alternativo)

### Comando básico:
```bash
adb logcat -s AndroidRuntime:E
```

### Ver solo errores de tu app:
```bash
adb logcat --pid=$(adb shell pidof -s com.ar.edu.ort.parcial)
```

---

## 🚨 Errores Comunes con Firebase

### 1. **google-services.json no configurado correctamente**
**Error típico:**
```
Default FirebaseApp is not initialized
```
**Solución:**
- Verifica que `applicationId` coincida con el package en `google-services.json`
- Asegúrate que el archivo está en `app/google-services.json`

### 2. **Plugin de Google Services no aplicado**
**Error típico:**
```
Could not find com.google.firebase:firebase-common
```
**Solución:**
- Verifica que tienes `alias(libs.plugins.google.services)` en `app/build.gradle.kts`

### 3. **Crashlytics sin plugin**
**Error típico:**
```
FirebaseCrashlytics component is not present
```
**Solución:**
- Removí Crashlytics de las dependencias (ya lo hice ✅)

### 4. **Messaging sin configuración**
**Error típico:**
```
Service Intent must be explicit
```
**Solución:**
- Removí Messaging de las dependencias (ya lo hice ✅)

---

## ✅ LO QUE YA CORREGÍ

1. ✅ Removí `firebase-crashlytics` de las dependencias
2. ✅ Removí `firebase-messaging` de las dependencias
3. ✅ Dejé solo las dependencias esenciales:
   - Analytics
   - Authentication
   - Firestore
   - Storage

---

## 🔧 Próximos Pasos

### 1. Sync Gradle
```
File → Sync Project with Gradle Files
```

### 2. Limpiar y Reconstruir
```
Build → Clean Project
Build → Rebuild Project
```

### 3. Ejecutar la app nuevamente

### 4. Si sigue crasheando:

**Mira el Logcat y busca:**
- Líneas rojas con "E/" al inicio
- `FATAL EXCEPTION`
- `Caused by:`

**Y compárteme:**
- El mensaje de error completo
- El stacktrace (las líneas que empiezan con "at ...")

---

## 📊 Atajos Útiles de Logcat

| Filtro | Qué muestra |
|--------|-------------|
| `Error` | Solo errores críticos |
| `Warn` | Advertencias y errores |
| `Info` | Información general |
| `Debug` | Mensajes de depuración |
| `Verbose` | TODO (mucha información) |

### Buscar texto específico:
- Usa la barra de búsqueda en Logcat
- Busca palabras clave como: "Exception", "Error", "Firebase", "Crash"

---

## 💡 Tips Adicionales

1. **Limpiar Logcat**: Click en el ícono de "basura" para limpiar el log antes de ejecutar la app
2. **Pausar Logcat**: Click en el ícono de "pausa" para congelar el log y leer con calma
3. **Scroll Lock**: Desactiva el scroll automático cuando quieras quedarte en una línea específica
4. **Copiar logs**: Selecciona las líneas de error y copia con Ctrl+C para compartirlas

---

## 🎯 Ejemplo Real de Cómo Leer un Error

```
2025-10-29 10:30:45.123 12345-12345/com.ar.edu.ort.parcial E/AndroidRuntime: FATAL EXCEPTION: main
    Process: com.ar.edu.ort.parcial, PID: 12345
    java.lang.RuntimeException: Unable to start activity ComponentInfo{...}
        at android.app.ActivityThread.performLaunchActivity(...)
        at android.app.ActivityThread.handleLaunchActivity(...)
    Caused by: com.google.firebase.FirebaseException: Firebase not initialized
        at com.google.firebase.FirebaseApp.getInstance(FirebaseApp.java:123)
        at ort.argentina.yatay.tp3.tp3_parcial_grupal3.MainActivity.onCreate(MainActivity.kt:25)
```

**Lectura:**
1. **Error**: `RuntimeException: Unable to start activity`
2. **Causa raíz**: `FirebaseException: Firebase not initialized`
3. **Ubicación**: `MainActivity.kt:25` (línea 25 del MainActivity)
4. **Solución**: Firebase no está inicializado, revisar `google-services.json`

---

## 📞 ¿Necesitas ayuda?

Si después de seguir esta guía sigues teniendo problemas:
1. Abre Logcat
2. Ejecuta la app
3. Copia TODO el error desde `FATAL EXCEPTION` hasta el final
4. Compártelo conmigo

¡Así podré ayudarte mejor! 🚀

