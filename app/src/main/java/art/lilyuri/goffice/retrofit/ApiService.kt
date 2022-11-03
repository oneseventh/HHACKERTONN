package art.lilyuri.goffice.retrofit

import art.lilyuri.goffice.data.*
import art.lilyuri.goffice.management.TimeListData
import art.lilyuri.goffice.utils.CompanyBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @POST("/user/signup")
    fun signUp(@Body body: SignUpBody): Call<MsgData>

    @POST("/user/login")
    fun login(@Body body: LoginBody): Call<TokenData>

    @POST("/company/add")
    fun addCompany(@Body body: CompanyBody): Call<CompanyData>

    @POST("/post")
    fun writeArticle(@Body body: ArticleBody, @Header("access-token") token: String?): Call<ArticleData>

    @GET("/user/mypage")
    fun getProfile(
        @Header("access_token") token: String?
    ): Call<MyData>

    @GET("/post/{number}")
    fun readArticle(@Path("number") number : Int, @Header("access-token") token: String?): Call<ArticleData>

    @GET("/post/")
    fun readAllArticle(@Header("access-token") token: String?): Call<ArrayList<ArticleAllData>>

    @GET("/admin/member")
    fun getMemberList(@Header("access-token") token: String?): Call<MembersData>

    @POST("")

    @GET("")
    fun getWeekTime():Call<List<TimeListData>>
}