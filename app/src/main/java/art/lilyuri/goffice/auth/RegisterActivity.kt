package art.lilyuri.goffice.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.MainActivity
import art.lilyuri.goffice.data.LoginBody
import art.lilyuri.goffice.data.MsgData
import art.lilyuri.goffice.data.SignUpBody
import art.lilyuri.goffice.data.TokenData
import art.lilyuri.goffice.databinding.ActivityLoginBinding
import art.lilyuri.goffice.databinding.ActivityRegisterBinding
import art.lilyuri.goffice.register.RegisterCompany
import art.lilyuri.goffice.retrofit.RetrofitAPI
import art.lilyuri.goffice.sharedpreferences.SharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("Error", "Eroor")
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val email: String = binding.etEmail.text.toString()
            val password: String = binding.etPassword.text.toString()
            val company: String = binding.etCompany.text.toString()
            val department: String = binding.etDepartment.text.toString()
            val position: String = binding.etPosition.text.toString()
            val phone: String = binding.etPhone.text.toString()
            val name: String = binding.etName.text.toString()
            val data = SignUpBody(email, password, company, department, position, name, phone)
            val call = RetrofitAPI.getApiService().signUp(data)
            call.enqueue(object : Callback<MsgData> {
                override fun onResponse(call: Call<MsgData>, response: Response<MsgData>) {
                    if (response.isSuccessful) {
                        val call2 = RetrofitAPI.getApiService().login(LoginBody(email, password))
                        call2.enqueue(object : Callback<TokenData> {
                            override fun onResponse(
                                call: Call<TokenData>,
                                response: Response<TokenData>
                            ) {
                                SharedPreferences.prefs.setString(
                                    "token",
                                    response.body()!!.accessToken
                                );
                                goMain()

                            }

                            override fun onFailure(call: Call<TokenData>, t: Throwable) {

                            }
                        })
                    }
                }

                override fun onFailure(call: Call<MsgData>, t: Throwable) {

                }
            })

        }

        binding.tvError.setOnClickListener {
            if (binding.tvError.text == "혹시 회사를 등록 하실건가요? 등록하기") {
                startActivity(Intent(this, RegisterCompany::class.java))
                finish()
            }
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
