package ru.int24.ownbarbershop.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.int24.ownbarbershop.config.DefConfig

class UseRetrofit {

    companion object{

        fun makeRetrofitAPI(): ApiYclients{

            val myRetrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(DefConfig.url)
                    .build()
            return myRetrofit.create(ApiYclients::class.java)
        }

    }

}
