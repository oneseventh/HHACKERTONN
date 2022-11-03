package art.lilyuri.goffice.retrofit

import art.lilyuri.goffice.management.TimeListData
import art.lilyuri.goffice.data.LoginBody
import art.lilyuri.goffice.data.SignUpBody
import art.lilyuri.goffice.data.TokenData
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @POST("/auth/new")
    fun signUp(@Body body: SignUpBody): Call<TokenData>

    @POST("/auth/local")
    fun login(@Body body: LoginBody): Call<TokenData>

    @GET("")
    fun getWeekTime():Call<List<TimeListData>>
}