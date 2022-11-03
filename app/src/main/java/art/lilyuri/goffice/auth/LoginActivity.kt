package art.lilyuri.goffice.auth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.databinding.ActivityBoardlistBinding
import art.lilyuri.goffice.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email: String = binding.etEmail.text.toString()
            val password: String = binding.etPassword.text.toString()
            //Request

        }


    }

    fun showErrorMsg(msg: String) {
        binding.tvError.text = msg
        binding.tvError.visibility = if (binding.tvError.visibility == View.INVISIBLE) View.VISIBLE else View.INVISIBLE
    }
}