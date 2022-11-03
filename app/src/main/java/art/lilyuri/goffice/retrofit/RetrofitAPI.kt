package art.lilyuri.goffice.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAPI {
    private var retrofit: Retrofit? = null;
    private const val baseUrl = "https://goffice-midas.herokuapp.com/";
    private fun getRetrofit(): Retrofit {
        if(retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!;
    }

    fun getApiService():ApiService{
        return getRetrofit().create(ApiService::class.java)
    }
}