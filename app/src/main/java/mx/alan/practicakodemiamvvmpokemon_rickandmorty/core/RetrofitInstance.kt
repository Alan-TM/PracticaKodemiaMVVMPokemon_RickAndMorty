package mx.alan.practicakodemiamvvmpokemon_rickandmorty.core

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    fun getRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            //   .addInterceptor(AuthInterceptor(context = context))
            .build()
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitRickMorty(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            //   .addInterceptor(AuthInterceptor(context = context))
            .build()
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /*class AuthInterceptor(context: Context) : Interceptor {
        //   private val shared = SharedPreferencesInstance.getInstance(context)

        override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()

            // If token has been saved, add it to the request

            // shared.getToken()?.let {
            requestBuilder.addHeader("Authorization","Barer ")
            // }
            /* sessionManager.fetchAuthToken()?.let {
                 requestBuilder.addHeader("Authorization", "Bearer $it")
             }*/

            return chain.proceed(requestBuilder.build())
        }
    }*/

}