package art.lilyuri.goffice.article

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.R
import art.lilyuri.goffice.data.ArticleBody
import art.lilyuri.goffice.data.ArticleData
import art.lilyuri.goffice.databinding.ActivityMakearticleBinding
import art.lilyuri.goffice.retrofit.RetrofitAPI
import art.lilyuri.goffice.sharedpreferences.SharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMakearticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakearticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var category: Int = 5

        binding.categoryRadio.setOnCheckedChangeListener { _, i ->
            category = when (i) {
                R.id.categoryLunch -> 1
                R.id.categoryCoffee -> 2
                R.id.categoryTexi -> 3
                R.id.categoryCar -> 4
                else -> 5
            }
        }

        binding.articleWriteButton.setOnClickListener {
            if (binding.postName.text.toString().length < 2 || binding.postContent.text.toString().length < 2) {
                Toast.makeText(this, "제목이나 내용이 너무 짧습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            AlertDialog.Builder(this).setTitle(binding.postName.text)
                .setMessage(binding.postContent.text)
                .setPositiveButton("확인") { _, _ ->
                    if (binding.postName.text.toString().length < 2 || binding.postContent.text.toString().length < 2) {
                        Toast.makeText(this, "본문이나, 제목이 너무 짧아요", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }
//                    Log.e("token", SharedPreferences.prefs.getString("token", ""))
                    val call = RetrofitAPI.getApiService().writeArticle(
                        ArticleBody(
                            binding.postName.text.toString(),
                            binding.postContent.text.toString(), 1
                        ),
                        SharedPreferences.prefs.getString("token", "")
                    )
                    call.enqueue(object : Callback<ArticleData> {
                        override fun onResponse(
                            call: Call<ArticleData>,
                            response: Response<ArticleData>
                        ) {
                            Toast.makeText(
                                this@ArticleWriteActivity,
                                "게시글이 작성 되었습니다!",
                                Toast.LENGTH_SHORT
                            ).show()
                            println(response.message())
                            finish()
                        }

                        override fun onFailure(call: Call<ArticleData>, t: Throwable) {
                            Toast.makeText(
                                this@ArticleWriteActivity,
                                "오류가 발생했습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                            println("ㄴㄴ")
                        }

                    })
                    finish()
                }.show()
        }
    }
}