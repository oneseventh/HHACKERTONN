package art.lilyuri.goffice.retrofit

import art.lilyuri.goffice.Admin.TimeListData
import art.lilyuri.goffice.data.LoginBody
import art.lilyuri.goffice.data.MsgData
import art.lilyuri.goffice.data.SignUpBody
import art.lilyuri.goffice.data.TokenData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("/user/signup")
    fun signUp(@Body body: SignUpBody): Call<MsgData>

    @POST("/user/login")
    fun login(@Body body: LoginBody): Call<TokenData>

    @GET("")
    fun getWeekTime():Call<List<TimeListData>>
}