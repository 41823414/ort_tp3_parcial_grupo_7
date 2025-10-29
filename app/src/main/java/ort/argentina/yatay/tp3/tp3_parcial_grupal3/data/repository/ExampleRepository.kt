package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Ejemplo de Repository con inyección de dependencias usando @Inject
 * La anotación @Inject en el constructor indica a Hilt/Dagger cómo crear esta clase
 */
@Singleton
class ExampleRepository @Inject constructor(
    // Aquí puedes inyectar otras dependencias como APIs, DAOs, etc.
    // private val apiService: ApiService,
    // private val database: AppDatabase
) : ExampleRepositoryInterface {

    override fun getData(): String {
        return "Datos desde el repositorio con Hilt/Dagger"
    }

    override suspend fun fetchDataFromApi(): Result<String> {
        // Simulación de llamada a API
        return try {
            Result.success("Datos obtenidos exitosamente con Dagger/Hilt")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

