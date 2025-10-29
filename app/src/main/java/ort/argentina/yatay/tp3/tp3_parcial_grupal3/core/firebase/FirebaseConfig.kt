package ort.argentina.yatay.tp3.tp3_parcial_grupal3.core.firebase

import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject
import javax.inject.Singleton

/**
 * FIREBASE - Configuración centralizada de Firebase
 * Proporciona acceso a los servicios de Firebase de manera organizada
 */
@Singleton
class FirebaseConfig @Inject constructor() {

    /**
     * Firebase Authentication
     */
    val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    /**
     * Firebase Firestore Database
     */
    val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    /**
     * Firebase Storage
     */
    val storage: FirebaseStorage by lazy {
        FirebaseStorage.getInstance()
    }

    /**
     * Firebase Analytics
     */
    fun getAnalytics(context: android.content.Context): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(context)
    }

    /**
     * Verifica si Firebase está inicializado
     */
    fun isInitialized(context: android.content.Context): Boolean {
        return FirebaseApp.getApps(context).isNotEmpty()
    }
}

