package art.lilyuri.goffice.register

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.R
import art.lilyuri.goffice.data.TokenData
import art.lilyuri.goffice.databinding.ActivityRegisterCompanyBinding
import art.lilyuri.goffice.retrofit.RetrofitAPI
import art.lilyuri.goffice.sharedpreferences.SharedPreferences
import art.lilyuri.goffice.utils.CompanyBody
import art.lilyuri.goffice.utils.CompanyData
import art.lilyuri.goffice.utils.CompanyType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterCompany : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterCompanyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var isChecked = false

        var companyName: String
        var coreTime: Int
        var type: CompanyType = CompanyType.선택적출퇴근제
        binding.etRadio.setOnCheckedChangeListener { _, i ->
            isChecked = true
            type = when (i) {
                R.id.category1 -> CompanyType.선택적출퇴근제
                else -> CompanyType.선택적출퇴근제
            }
        }


        binding.btnLogin.setOnClickListener {
            if (type != null) {
                companyName = binding.etCompany.text.toString()
                coreTime = binding.etCore.text.toString().toInt()
                val data = CompanyBody(companyName, coreTime, type.value)
                val call = RetrofitAPI.getApiService().addCompany(data)
                call.enqueue(object : Callback<art.lilyuri.goffice.data.CompanyData> {
                    override fun onResponse(
                        call: Call<art.lilyuri.goffice.data.CompanyData>,
                        response: Response<art.lilyuri.goffice.data.CompanyData>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@RegisterCompany, "회사 등록 성공", Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        } else {
                            Toast.makeText(this@RegisterCompany, "회사 등록 실패", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(
                        call: Call<art.lilyuri.goffice.data.CompanyData>,
                        t: Throwable
                    ) {
                        Toast.makeText(this@RegisterCompany, "회사 등록 실패", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this, "회사 근무 타입을 선택해 주세요!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}