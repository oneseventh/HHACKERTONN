package art.lilyuri.goffice.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.MainActivity
import art.lilyuri.goffice.data.LoginBody
import art.lilyuri.goffice.data.MyData
import art.lilyuri.goffice.data.TokenData
import art.lilyuri.goffice.databinding.ActivityLoginBinding
import art.lilyuri.goffice.retrofit.RetrofitAPI
import art.lilyuri.goffice.sharedpreferences.SharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvReg.setOnClickListener {
            Log.e("Start", "Init start")
            startActivity(Intent(this, RegisterActivity::class.java))
            Log.e("Final", "End")
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val email: String = binding.etEmail.text.toString()
            val password: String = binding.etPassword.text.toString()
            //Request
            val data = LoginBody(email, password)
            val call = RetrofitAPI.getApiService().login(data)
            call.enqueue(object : Callback<TokenData> {
                override fun onResponse(call: Call<TokenData>, response: Response<TokenData>) {
                    if(response.isSuccessful) {
                        SharedPreferences.prefs.setString("token", response.body()!!.accessToken)
                        val call1 = RetrofitAPI.getApiService().getProfile(SharedPreferences.prefs.getString("token", ""))
                        call1.enqueue(object : Callback<MyData> {
                            override fun onResponse(
                                call: Call<MyData>,
                                response: Response<MyData>
                            ) {
                                SharedPreferences.prefs.setString("userid", response.body()!!.index.toString())
                                SharedPreferences.prefs.setString("name", response.body()!!.name)
                                SharedPreferences.prefs.setString("email", response.body()!!.email)
                                if (response.body()!!._postion == "사원") {
                                    SharedPreferences.prefs.setString("admin", false.toString())
                                } else if (response.body()!!._postion == "관리자") {
                                    SharedPreferences.prefs.setString("admin", true.toString())
                                }
                                SharedPreferences.prefs.setString("depart", response.body()!!.department)

                            }

                            override fun onFailure(call: Call<MyData>, t: Throwable) {
                                Toast.makeText(this@LoginActivity, "오류 발생", Toast.LENGTH_SHORT).show()
                            }

                        })
                        goMain()
                    } else {
                        showErrorMsg("로그인 실패")
                    }

                }

                override fun onFailure(call: Call<TokenData>, t: Throwable) {
                    showErrorMsg("로그인 실패")
                }
            } )

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