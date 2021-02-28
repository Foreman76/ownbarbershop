package ru.int24.ownbarbershop.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path
import retrofit2.http.Query
import ru.int24.ownbarbershop.models.data.ServicesNet

interface ApiYclients {

    @GET("/api/v1/book_services/{companyid}")
    suspend fun getServices(@HeaderMap headers: Map<String, String>,
                            @Path("companyid") companyid:Int,
                            @Query("staff_id") staff_id:Int?,
                            @Query("datetime") datetime:String?,
                            @Query("service_ids") service_ids:String?): Response<ServicesNet>

}

