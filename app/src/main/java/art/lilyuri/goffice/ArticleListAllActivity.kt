package art.lilyuri.goffice

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.databinding.ActivityAllboardBinding
import art.lilyuri.goffice.databinding.ActivityBoardlistBinding
import art.lilyuri.goffice.utils.ArticleAdapter
import art.lilyuri.goffice.utils.ArticleData

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
            add(ArticleData(idx = 0, articleCategory = 0, articleName = "안녕하세요 ㅋㅋ", articleAuthor = "곽**", articleComment = 0, articleDate = "0000-00-00 00:00:00", articleContent = "dd"))
            add(ArticleData(idx = 0, articleCategory = 0, articleName = "여러분들라면먹지마세요체질이란게바뀝니다", articleAuthor = "먹**", articleComment = 99, articleDate = "0000-00-00 00:00:00", articleContent = "dd"))
            add(ArticleData(idx = 0, articleCategory = 0, articleName = "냐아 도키도키시데타~", articleAuthor = "테**", articleComment = 0, articleDate = "0000-00-00 00:00:00", articleContent = "dd"))
            add(ArticleData(idx = 0, articleCategory = 0, articleName = "그것도모르는건가다음에알려주도록하지", articleAuthor = "켈*", articleComment = 0, articleDate = "0000-00-00 00:00:00", articleContent = "dd"))

            adapter.datas = datas
            adapter.notifyDataSetChanged()
        }
    }
}