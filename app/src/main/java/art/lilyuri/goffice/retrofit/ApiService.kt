package art.lilyuri.goffice.retrofit

import art.lilyuri.goffice.data.*
import art.lilyuri.goffice.management.TimeListData
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @POST("/user/signup")
    fun signUp(@Body body: SignUpBody): Call<MsgData>

    @POST("/user/login")
    fun login(@Body body: LoginBody): Call<TokenData>

    @POST("/commute/start")
    fun startWork(@Header("access-token")token: String): Call<MsgData>

    @POST("/commute/end")
    fun endWork(@Header("access-token")token: String): Call<MsgData>

    @GET("/commute/current")
    fun getCurrentStartedTime(@Header("access-token")token: String): Call<TimeData>

    @GET("")
    fun getWeekTime():Call<List<TimeListData>>
}