package art.lilyuri.goffice.article

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.databinding.ActivityArticleviewBinding

class ArticleViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView()

        val author = intent.getSerializableExtra("author") as String
        val name = intent.getSerializableExtra("name") as String
        val content = intent.getSerializableExtra("content") as String
        val date = intent.getSerializableExtra("date") as String
        val category = intent.getSerializableExtra("category") as Int
        val idx = intent.getSerializableExtra("idx") as Int
        val comment = intent.getSerializableExtra("comment") as Int

        binding.articleN.text = name
        binding.articleA.text = author
        binding.articleC.text = content

    }
}