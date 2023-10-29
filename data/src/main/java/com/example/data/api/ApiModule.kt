package com.example.data.api

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideLoggingInterceptor() : HttpLoggingInterceptor{
        val loggingInterceptor = HttpLoggingInterceptor{
            Log.e("api", it)
        }
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return  loggingInterceptor
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor,
    tokenInterceptor: TokenInterceptor
                            ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(tokenInterceptor)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://ecommerce.routemisr.com/")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun getWebServices(retrofit: Retrofit) : WebServices{
        return  retrofit.create(WebServices::class.java)
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) : SharedPreferences{
        return  context.getSharedPreferences("prefences", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideTokenInterceptor(sharedPreferences: SharedPreferences) : Interceptor{
        return  TokenInterceptor(sharedPreferences)
    }

    @Provides
    fun provideIoDispatcher():CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideIoScope(coroutineDispatcher: CoroutineDispatcher): CoroutineScope{
        return CoroutineScope(coroutineDispatcher)
    }

    @Provides
    fun provideAuthenticator(webServices: WebServices,scope: CoroutineScope,sharedPreferences: SharedPreferences):Authenticator{
        return AuthenticateInterceptor(webServices,scope,sharedPreferences)
    }
}
