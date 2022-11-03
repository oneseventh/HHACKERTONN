package art.lilyuri.goffice

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.databinding.ActivityBoardlistBinding
import art.lilyuri.goffice.utils.ArticleAdapter
import art.lilyuri.goffice.utils.ArticleData
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardlistBinding

    lateinit var adapter: ArticleAdapter
    val datas = mutableListOf<ArticleData>()

    private val ment: List<String> = listOf("현재 이번주 연장 근무 시간은 0시간 입니다", "스트레칭은 필수!", "한국인은 밥심!!", "과도한 업무는 건강에 좋지 않아요!")

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ArticleAdapter(this)
        binding.dupal.adapter = adapter

        datas.apply {
            add(ArticleData(idx = 0, articleCategory = 0, articleName = "안녕하세요 ㅋㅋ", articleAuthor = "곽**", articleComment = 0, articleDate = "0000-00-00 00:00:00", articleContent = "dd"))
            add(ArticleData(idx = 0, articleCategory = 0, articleName = "여러분들라면먹지마세요체질이란게바뀝니다", articleAuthor = "먹**", articleComment = 99, articleDate = "0000-00-00 00:00:00", articleContent = "dd"))
            add(ArticleData(idx = 0, articleCategory = 0, articleName = "냐아 도키도키시데타~", articleAuthor = "테**", articleComment = 0, articleDate = "0000-00-00 00:00:00", articleContent = "dd"))
            add(ArticleData(idx = 0, articleCategory = 0, articleName = "그것도모르는건가다음에알려주도록하지", articleAuthor = "켈*", articleComment = 0, articleDate = "0000-00-00 00:00:00", articleContent = "dd"))

            adapter.datas = datas
            adapter.notifyDataSetChanged()
        }
    }

    private fun <T> List<T>.random() : T {
        val random = Random().nextInt((size))
        return get(random)
    }
}