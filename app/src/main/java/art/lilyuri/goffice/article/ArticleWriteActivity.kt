package art.lilyuri.goffice.article

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.databinding.ActivityMakearticleBinding

class ArticleWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMakearticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakearticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.articleWriteButton.setOnClickListener {
            if (binding.postName.text.toString().length < 2 || binding.postContent.text.toString().length < 2) {
                Toast.makeText(this, "제목이나 내용이 너무 짧습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            AlertDialog.Builder(this).setTitle(binding.postName.text).setMessage(binding.postContent.text)
                .setPositiveButton("확인") { _, _ -> finish() }.show()
        }
    }
}