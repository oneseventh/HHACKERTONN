package art.lilyuri.goffice.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.MainActivity
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
//            val data = LoginBody(email, password)
//            val call = RetrofitAPI.getApiService().login(data)
//            call.enqueue(object : Callback<TokenData> {
//                override fun onResponse(call: Call<TokenData>, response: Response<TokenData>) {
//                    if(response.isSuccessful) {
//                        SharedPreferences.prefs.setString("token", response.body()!!.token)
//                        goMain()
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

    fun showErrorMsg(msg: String) {
        binding.tvError.text = msg
        binding.tvError.visibility = View.VISIBLE
    }

    fun goMain() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }
}