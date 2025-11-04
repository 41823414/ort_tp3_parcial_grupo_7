package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.api

import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.AuthLoginRequest
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.AuthLoginResponse
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.AuthSignUpRequest
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.AuthSignUpResponse
import retrofit2.Response
import retrofit2.http.*

/**
 * API Service - Endpoints de autenticación del proyecto TP
 * 
 * Los endpoints se conectan a la API real configurada en BuildConfig.BASE_URL
 * Durante desarrollo, se usa Postman Mock Server para simular respuestas
 */
interface ApiService {

    /**
     * Login
     * POST /auth/login
     * Retorna: { "token": "..." }
     */
    @POST("auth/login")
    suspend fun login(@Body body: AuthLoginRequest): Response<AuthLoginResponse>

    /**
     * SignUp/Create Account
     * POST /auth/create
     * Retorna: Usuario completo con id, email, username, name, address, phone
     */
    @POST("auth/create")
    suspend fun signUp(@Body body: AuthSignUpRequest): Response<AuthSignUpResponse>
}

