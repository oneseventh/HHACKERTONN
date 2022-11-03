package art.lilyuri.goffice.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import art.lilyuri.goffice.MainActivity
import art.lilyuri.goffice.auth.LoginActivity
import art.lilyuri.goffice.databinding.ActivityRegisterBinding
import art.lilyuri.goffice.databinding.ActivitySplashBinding
import art.lilyuri.goffice.sharedpreferences.SharedPreferences

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getToken()

    }

    private fun getToken() {
        token = SharedPreferences.prefs.getString("token", "")
        if(token == "") {
            val handler = Handler()
            handler.postDelayed(Runnable {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        } else {
            val handler = Handler()
            handler.postDelayed(Runnable {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }
    }
}