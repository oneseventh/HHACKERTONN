package art.lilyuri.goffice.article

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.databinding.ActivityArticleviewBinding
import art.lilyuri.goffice.databinding.TimeListItemBinding

class TimeListView : AppCompatActivity() {

    private lateinit var binding: TimeListItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TimeListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView()

        val core = intent.getSerializableExtra("core") as Int
        val name = intent.getSerializableExtra("name") as String

        binding.timeListName.text = name
        binding.timeListTotal.text = core.toString()

    }
}