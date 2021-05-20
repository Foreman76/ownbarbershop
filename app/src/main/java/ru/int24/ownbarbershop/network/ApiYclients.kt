package ru.int24.ownbarbershop.network

import retrofit2.Response
import retrofit2.http.*
import ru.int24.ownbarbershop.models.data.*

interface ApiYclients {

    @GET("/api/v1/book_services/{companyid}")
    suspend fun getServices(@HeaderMap headers: Map<String, String>,
                            @Path("companyid") companyid: Int,
                            @Query("staff_id") staff_id: Int?,
                            @Query("datetime") datetime: String?,
                            @Query("service_ids") service_ids: String?): Response<ServicesNet>

    @GET("/api/v1/book_staff/{companyid}")
    suspend fun getStaff(@HeaderMap headers: Map<String, String>,
                         @Path("companyid") companyid: Int,
                         @Query("datetime") datetime: String?,
                         @Query("service_ids") service_ids: MutableList<String>?): Response<StaffNet>


    @GET("/api/v1/book_times/{companyid}/{staff_id}/{datetime}")
    suspend fun getSession(@HeaderMap headers: Map<String, String>,
                           @Path("companyid") companyid: Int,
                           @Path("staff_id") staff_id: Int,
                           @Path("datetime") datetime: String?,
                           @Query("service_ids") service_ids: MutableList<String>?): Response<SessionNet>


    @GET("/api/v1/book_dates/{companyid}")
    suspend fun getWorkDays(@HeaderMap headers: Map<String, String>,
                            @Path("companyid") companyid: Int,
                            @Query("staff_id") staff_id: Int?,
                            @Query("service_ids") service_ids: MutableList<String>?): Response<WorkDaysNet>

    @POST("/api/v1/book_code/{companyid}")
    suspend fun getSMSCode(@HeaderMap headers: Map<String, String>,
                           @Path("companyid") companyid: Int,
                           @Body requestSMSCode: RequestSMSCodeNet): Response<RespSMSCodeNet>

    @POST("/api/v1/user/auth")
    suspend fun getAuthUser(@HeaderMap headers: Map<String, String>,
                            @Body requestAuth: RequestAuthNet): Response<UserAuthNet>


    @POST("/api/v1/book_record/{companyid}")
    suspend fun createUserOrder(@HeaderMap headers: Map<String, String>,
                                @Path("companyid") companyid: Int,
                                @Body requestRecord: RequestRecordNet): Response<RespOrderNet>

    @GET("/api/v1/user/records/")
    suspend fun getUserRecords(@HeaderMap headers: Map<String, String>):Response<RecordsNet>

}

