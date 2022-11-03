package art.lilyuri.goffice.management

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.data.MemberData
import art.lilyuri.goffice.databinding.TimeListBinding
import art.lilyuri.goffice.retrofit.RetrofitAPI
import art.lilyuri.goffice.sharedpreferences.SharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Objects

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: TimeListBinding

    private lateinit var adapter: TimeListAdapter
    private val datas = mutableListOf<TimeListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TimeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val call = RetrofitAPI.getApiService().getMemberList(SharedPreferences.prefs.getString("token", ""))
        call.enqueue(object : Callback<ArrayList<MemberData>> {
            override fun onResponse(call: Call<ArrayList<MemberData>>, response: Response<ArrayList<MemberData>>) {
                println(response.body().toString())
            }

            override fun onFailure(call: Call<ArrayList<MemberData>>, t: Throwable) {
                println(t.message)
            }

        })

//        adapter = TimeListAdapter(this)
//        binding.timeListRecycler.adapter = adapter
//
//        datas.apply {
//            add(TimeListData(userName = "ddd", weekTime = 4))
//
//            adapter.datas = datas
//            adapter.notifyDataSetChanged()
//        }
    }
}