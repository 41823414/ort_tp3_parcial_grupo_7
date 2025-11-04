package ort.argentina.yatay.tp3.tp3_parcial_grupal3.data.remote.mock

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * Interceptor que mockea el endpoint de login para desarrollo
 */
class MockAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath

        return if (request.method == "POST" && path.endsWith("/auth/login")) {
            val json = """
                {
                  "token": "mock-token-123",
                  "user": {
                    "id": 1,
                    "name": "Agus",
                    "email": "agustin.damian.montenegro@gmail.com"
                  }
                }
            """.trimIndent()
            Response.Builder()
                .code(200)
                .message("OK")
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .body(json.toResponseBody("application/json".toMediaType()))
                .build()
        } else {
            chain.proceed(request)
        }
    }
}


