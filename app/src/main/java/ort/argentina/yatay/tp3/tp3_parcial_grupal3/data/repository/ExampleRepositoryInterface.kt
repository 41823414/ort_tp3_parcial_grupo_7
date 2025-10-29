package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.repository

/**
 * Interfaz para demostrar el uso de @Binds en Dagger/Hilt
 * Este patrón permite cambiar fácilmente la implementación sin modificar el código que la usa
 */
interface ExampleRepositoryInterface {
    fun getData(): String
    suspend fun fetchDataFromApi(): Result<String>
}

