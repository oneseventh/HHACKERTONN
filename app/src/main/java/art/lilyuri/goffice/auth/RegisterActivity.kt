package art.lilyuri.goffice.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.MainActivity
import art.lilyuri.goffice.data.SignUpBody
import art.lilyuri.goffice.databinding.ActivityRegisterBinding
import art.lilyuri.goffice.retrofit.RetrofitAPI

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvError.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, RegisterActivity::class.java))
            finish()
        }

        binding.btnRegister.setOnClickListener {
            val email: String = binding.etEmail.text.toString()
            val password: String = binding.etPassword.text.toString()
            val company: String = binding.etCompany.text.toString()
            val department: String = binding.etDepartment.text.toString()
            val position: String = binding.etPosition.text.toString()

            val data = SignUpBody(email, password, company, department, position)
            val call = RetrofitAPI.getApiService().signUp(data)
//            call.enqueue(object : Callback<TokenData> {
//                override fun onResponse(call: Call<TokenData>, response: Response<TokenData>) {
//                    if(response.isSuccessful) {
//                        SharedPreferences.prefs.setString("token", response.body()!!.token)
//                        goMain();
//                    } else {
//                        showErrorMsg("로그인 실패")
//                    }
//
//                }
//
//                override fun onFailure(call: Call<TokenData>, t: Throwable) {
//                    showErrorMsg("로그인 실패")
//                }
//            } )

        }



    }

    fun goMain() {
        startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
        finish()
    }

    fun showErrorMsg(msg: String) {
        binding.tvError.text = msg
        binding.tvError.visibility = View.VISIBLE
    }
}