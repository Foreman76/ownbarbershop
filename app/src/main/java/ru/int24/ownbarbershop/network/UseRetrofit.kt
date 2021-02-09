package ru.int24.ownbarbershop.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UseRetrofit {

    companion object{

        fun makeRetrofitAPI(): ApiYclients{

            val myRetrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.yclients.com/")
                    .build()
            return myRetrofit.create(ApiYclients::class.java)
        }

    }

}
