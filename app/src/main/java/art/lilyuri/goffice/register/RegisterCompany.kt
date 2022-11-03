package art.lilyuri.goffice.register

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.databinding.ActivityRegisterCompanyBinding

class RegisterCompany : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterCompanyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}