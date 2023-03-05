package network

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class RequestInterceptor(name: String, email: String) : Interceptor {
    private val credentials: String

    init {
        credentials = Credentials.basic(name, email)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val authenticatedRequest: Request = request.newBuilder()
            .header("User-Agent", credentials)
            .build()
        return chain.proceed(authenticatedRequest)
    }
}
