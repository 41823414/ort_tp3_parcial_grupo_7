# Guía de Room Database

## 📚 ¿Qué es Room?

**Room** es una biblioteca de persistencia que proporciona una capa de abstracción sobre SQLite. Facilita el trabajo con bases de datos locales en Android mediante:
- ✅ Verificación de consultas SQL en tiempo de compilación
- ✅ Conversión automática entre objetos Kotlin y tablas SQL
- ✅ Integración con LiveData y Flow para observación reactiva
- ✅ Soporte para corrutinas

## ✅ Configuración Completada

### Dependencias agregadas:
- `room-runtime` (2.6.1)
- `room-ktx` (2.6.1) - Extensiones de Kotlin con soporte para corrutinas
- `room-compiler` (2.6.1) - Procesador de anotaciones con **KSP** ⚡

### 🚀 Usando KSP (Kotlin Symbol Processing):
```kotlin
// Room ahora usa KSP en lugar de KAPT
ksp(libs.room.compiler)  // ⚡ 2x más rápido que kapt()
```

## 🏗️ Componentes de Room

### 1. **@Entity** - Entidades (Tablas)
Define una tabla en la base de datos:

```kotlin
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    
    @ColumnInfo(name = "name")
    val name: String,
    
    @ColumnInfo(name = "email")
    val email: String
)
```

**Anotaciones importantes:**
- `@Entity` - Define una tabla
- `@PrimaryKey` - Clave primaria (autoGenerate = true para auto-incremento)
- `@ColumnInfo` - Personaliza el nombre de la columna
- `@Ignore` - Excluye un campo de la tabla
- `@Index` - Crea índices para mejorar rendimiento

### 2. **@Dao** - Data Access Object
Define las operaciones de base de datos:

```kotlin
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long
    
    @Update
    suspend fun updateUser(user: UserEntity)
    
    @Delete
    suspend fun deleteUser(user: UserEntity)
    
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>
}
```

**Operaciones disponibles:**
- `@Insert` - Insertar registros
- `@Update` - Actualizar registros
- `@Delete` - Eliminar registros
- `@Query` - Consultas personalizadas SQL

**OnConflictStrategy:**
- `REPLACE` - Reemplaza el registro existente
- `IGNORE` - Ignora el conflicto
- `ABORT` - Aborta la transacción (por defecto)

### 3. **@Database** - Base de datos
Define la configuración de la base de datos:

```kotlin
@Database(
    entities = [UserEntity::class, TaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao
}
```

## 🔄 Flow vs LiveData

Room soporta ambos, pero **Flow** es preferido en proyectos modernos:

```kotlin
// Con Flow (recomendado)
@Query("SELECT * FROM users")
fun getAllUsers(): Flow<List<UserEntity>>

// Con LiveData
@Query("SELECT * FROM users")
fun getAllUsers(): LiveData<List<UserEntity>>
```

**Ventajas de Flow:**
- Más flexible y componible
- Mejor integración con corrutinas
- Soporte para operadores como map, filter, etc.

## 🔗 Integración con Hilt

El módulo `DatabaseModule.kt` provee las dependencias:

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
    
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()
}
```

## 📦 Estructura del Proyecto Room

```
data/
├── local/
│   ├── entity/
│   │   ├── UserEntity.kt       # Tabla de usuarios
│   │   └── TaskEntity.kt       # Tabla de tareas
│   ├── dao/
│   │   ├── UserDao.kt          # Operaciones de usuarios
│   │   └── TaskDao.kt          # Operaciones de tareas
│   └── AppDatabase.kt          # Configuración de la BD
├── repository/
│   ├── UserRepository.kt       # Lógica de negocio
│   └── TaskRepository.kt
└── di/
    └── DatabaseModule.kt        # Inyección con Hilt
```

## 🎯 Patrón de Uso

### Capa DAO → Repository → ViewModel → UI

1. **DAO**: Define operaciones SQL
2. **Repository**: Lógica de negocio y abstracción
3. **ViewModel**: Maneja estado y UI
4. **Screen**: Muestra datos

## 💡 Ejemplos Incluidos

### Entidades creadas:
- ✅ **UserEntity** - Usuario con nombre, email, edad
- ✅ **TaskEntity** - Tarea con título, descripción, estado

### DAOs creados:
- ✅ **UserDao** - CRUD completo, búsqueda, filtros
- ✅ **TaskDao** - Gestión de tareas por usuario

### Repositorios:
- ✅ **UserRepository** - Operaciones de usuarios
- ✅ **TaskRepository** - Operaciones de tareas

### ViewModels:
- ✅ **UserViewModel** - ViewModel con Room + Flow

### Pantallas:
- ✅ **UserListScreen** - UI completa con CRUD

## 🔍 Consultas SQL Avanzadas

### Búsqueda con LIKE
```kotlin
@Query("SELECT * FROM users WHERE name LIKE '%' || :query || '%'")
fun searchUsers(query: String): Flow<List<UserEntity>>
```

### Ordenamiento
```kotlin
@Query("SELECT * FROM users ORDER BY created_at DESC")
fun getUsersOrderedByDate(): Flow<List<UserEntity>>
```

### Conteo
```kotlin
@Query("SELECT COUNT(*) FROM users")
suspend fun getUserCount(): Int
```

### Filtros con rango
```kotlin
@Query("SELECT * FROM users WHERE age BETWEEN :min AND :max")
fun getUsersByAge(min: Int, max: Int): Flow<List<UserEntity>>
```

## 🔄 Migraciones

Para producción, reemplaza `.fallbackToDestructiveMigration()` con migraciones apropiadas:

```kotlin
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE users ADD COLUMN phone TEXT")
    }
}

Room.databaseBuilder(...)
    .addMigrations(MIGRATION_1_2)
    .build()
```

## ⚡ Optimización

### Índices
```kotlin
@Entity(
    tableName = "tasks",
    indices = [Index(value = ["user_id"])]
)
```

### Transacciones
```kotlin
@Transaction
@Query("SELECT * FROM users")
suspend fun getUsersWithTasks(): List<UserWithTasks>
```

## 🚀 Próximos Pasos

1. **Sincroniza Gradle** para descargar las dependencias de Room
2. **Build el proyecto** para que KAPT genere el código
3. **Usa UserListScreen** para probar el CRUD completo
4. **Personaliza** las entidades según tu necesidad

## 🔗 Recursos

- [Documentación oficial de Room](https://developer.android.com/training/data-storage/room)
- [Codelabs de Room](https://developer.android.com/codelabs/android-room-with-a-view-kotlin)
- [Room con Flow](https://developer.android.com/kotlin/flow)

