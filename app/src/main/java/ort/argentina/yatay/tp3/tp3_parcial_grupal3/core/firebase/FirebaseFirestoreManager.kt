package ort.argentina.yatay.tp3.tp3_parcial_grupal3.core.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * FIREBASE FIRESTORE - Gestión de base de datos en la nube
 */
@Singleton
class FirebaseFirestoreManager @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    /**
     * Crear o actualizar un documento
     */
    suspend fun <T> setDocument(
        collection: String,
        documentId: String,
        data: T,
        merge: Boolean = false
    ): Result<Unit> {
        return try {
            if (merge) {
                firestore.collection(collection)
                    .document(documentId)
                    .set(data as Any, SetOptions.merge())
                    .await()
            } else {
                firestore.collection(collection)
                    .document(documentId)
                    .set(data as Any)
                    .await()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Obtener un documento
     */
    suspend fun <T> getDocument(
        collection: String,
        documentId: String,
        clazz: Class<T>
    ): Result<T?> {
        return try {
            val snapshot = firestore.collection(collection)
                .document(documentId)
                .get()
                .await()
            Result.success(snapshot.toObject(clazz))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Obtener todos los documentos de una colección
     */
    suspend fun <T> getCollection(
        collection: String,
        clazz: Class<T>
    ): Result<List<T>> {
        return try {
            val snapshot = firestore.collection(collection)
                .get()
                .await()
            val documents = snapshot.documents.mapNotNull { it.toObject(clazz) }
            Result.success(documents)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Obtener documentos con query
     */
    suspend fun <T> getCollectionWhere(
        collection: String,
        field: String,
        value: Any,
        clazz: Class<T>
    ): Result<List<T>> {
        return try {
            val snapshot = firestore.collection(collection)
                .whereEqualTo(field, value)
                .get()
                .await()
            val documents = snapshot.documents.mapNotNull { it.toObject(clazz) }
            Result.success(documents)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Eliminar un documento
     */
    suspend fun deleteDocument(
        collection: String,
        documentId: String
    ): Result<Unit> {
        return try {
            firestore.collection(collection)
                .document(documentId)
                .delete()
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Actualizar campos específicos de un documento
     */
    suspend fun updateDocument(
        collection: String,
        documentId: String,
        updates: Map<String, Any>
    ): Result<Unit> {
        return try {
            firestore.collection(collection)
                .document(documentId)
                .update(updates)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Agregar un documento con ID autogenerado
     */
    suspend fun <T> addDocument(
        collection: String,
        data: T
    ): Result<String> {
        return try {
            val docRef = firestore.collection(collection)
                .add(data as Any)
                .await()
            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Realizar operaciones en batch
     */
    suspend fun executeBatch(operations: (FirebaseFirestore) -> Unit): Result<Unit> {
        return try {
            operations(firestore)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

