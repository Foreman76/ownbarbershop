package ru.int24.ownbarbershop.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.int24.ownbarbershop.config.DefConfig

class UseRetrofit {

    companion object{

        val httpklient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()


        fun makeRetrofitAPI(): ApiYclients{

            val myRetrofit = Retrofit.Builder()
                    .client(httpklient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(DefConfig.url)
                    .build()
            return myRetrofit.create(ApiYclients::class.java)
        }

    }

}
