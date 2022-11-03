package art.lilyuri.goffice.auth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.databinding.ActivityLoginBinding
import art.lilyuri.goffice.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val email: String = binding.etEmail.text.toString()
            val password: String = binding.etPassword.text.toString()
            val company: String = binding.etCompany.text.toString()
            val department: String = binding.etDepartment.text.toString()
            val position: String = binding.etPosition.text.toString()

            //Request
        }


    }

    fun showErrorMsg(msg: String) {
        binding.tvError.text = msg
        binding.tvError.visibility = View.VISIBLE
    }
}