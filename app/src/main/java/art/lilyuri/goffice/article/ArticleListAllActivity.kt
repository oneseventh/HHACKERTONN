package art.lilyuri.goffice.article

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.data.ArticleAllData
import art.lilyuri.goffice.databinding.ActivityAllboardBinding
import art.lilyuri.goffice.retrofit.RetrofitAPI
import art.lilyuri.goffice.sharedpreferences.SharedPreferences
import art.lilyuri.goffice.utils.ArticleAdapter
import art.lilyuri.goffice.utils.ArticleData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleListAllActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllboardBinding

    lateinit var adapter: ArticleAdapter
    val datas = mutableListOf<ArticleData>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ArticleAdapter(this)
        binding.dupal.adapter = adapter

        datas.apply {
            val cal1 = RetrofitAPI.getApiService().readAllArticle(SharedPreferences.prefs.getString("token", ""))
            cal1.enqueue(object : Callback<ArrayList<ArticleAllData>> {
                override fun onResponse(
                    call: Call<ArrayList<ArticleAllData>>,
                    response: Response<ArrayList<ArticleAllData>>
                ) {
                    datas.apply {
                        println(response.body()!!.size)
                    }
                }

                override fun onFailure(call: Call<ArrayList<ArticleAllData>>, t: Throwable) {
                    println(t.stackTraceToString())
                    Toast.makeText(this@ArticleListAllActivity, "목록을 못불러옴", Toast.LENGTH_SHORT).show()
                }

            })
            adapter.datas = datas
            adapter.notifyDataSetChanged()
        }

        binding.writeButton.setOnClickListener {
            startActivity(Intent(this, ArticleWriteActivity::class.java))
        }
    }
}