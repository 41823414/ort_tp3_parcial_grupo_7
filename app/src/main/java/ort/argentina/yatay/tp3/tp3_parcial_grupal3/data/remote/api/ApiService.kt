package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.api

import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.UserDto
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.PostDto
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.AuthLoginRequest
import ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.dto.AuthLoginResponse
import retrofit2.Response
import retrofit2.http.*

/**
 * API Service para JSONPlaceholder (API de ejemplo gratuita)
 * Define los endpoints de la API REST
 */
interface ApiService {

    /**
     * Login (mockeado por interceptor)
     * POST https://<base>/auth/login
     */
    @POST("auth/login")
    suspend fun login(@Body body: AuthLoginRequest): Response<AuthLoginResponse>

    /**
     * Obtener lista de usuarios
     * GET https://jsonplaceholder.typicode.com/users
     */
    @GET("users")
    suspend fun getUsers(): Response<List<UserDto>>

    /**
     * Obtener un usuario por ID
     * GET https://jsonplaceholder.typicode.com/users/{id}
     */
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") userId: Int): Response<UserDto>

    /**
     * Obtener posts
     * GET https://jsonplaceholder.typicode.com/posts
     */
    @GET("posts")
    suspend fun getPosts(): Response<List<PostDto>>

    /**
     * Obtener posts de un usuario específico
     * GET https://jsonplaceholder.typicode.com/posts?userId={userId}
     */
    @GET("posts")
    suspend fun getPostsByUser(@Query("userId") userId: Int): Response<List<PostDto>>

    /**
     * Crear un nuevo post
     * POST https://jsonplaceholder.typicode.com/posts
     */
    @POST("posts")
    suspend fun createPost(@Body post: PostDto): Response<PostDto>

    /**
     * Actualizar un post
     * PUT https://jsonplaceholder.typicode.com/posts/{id}
     */
    @PUT("posts/{id}")
    suspend fun updatePost(
        @Path("id") postId: Int,
        @Body post: PostDto
    ): Response<PostDto>

    /**
     * Eliminar un post
     * DELETE https://jsonplaceholder.typicode.com/posts/{id}
     */
    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") postId: Int): Response<Unit>
}

