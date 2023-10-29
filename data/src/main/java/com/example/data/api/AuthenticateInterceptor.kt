package com.example.data.api

import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject


class AuthenticateInterceptor @Inject constructor(
    private val webServices : WebServices,
    private val scope: CoroutineScope,
    private val sharedPreferences: SharedPreferences
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request{
        val tokenResponse = webServices.refreshToken("asda").execute()
        val token = tokenResponse.body()?.data
        sharedPreferences.edit().putString("token",token) // bt7fz el token lma tlogin wa lma ytgdd
        return response.request.newBuilder().header(
            "token",
            token ?: ""
        ).build()
    }
}