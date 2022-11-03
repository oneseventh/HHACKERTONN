package art.lilyuri.goffice

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import art.lilyuri.goffice.Admin.TimeListAdapter
import art.lilyuri.goffice.Admin.TimeListData
import art.lilyuri.goffice.databinding.TimeListBinding
import art.lilyuri.goffice.retrofit.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimeList : AppCompatActivity() {

    private val timeListBinding : TimeListBinding by lazy { TimeListBinding.inflate(layoutInflater) }
    lateinit var timeListAdapter: TimeListAdapter
    var userTimeList = listOf<TimeListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(timeListBinding.root)
        timeListAdapter = TimeListAdapter()

        timeListAdapter.setList(userTimeList)
        timeListAdapter.notifyDataSetChanged()

        timeListBinding.timeListRecycler.apply {
            adapter = timeListAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

    }

    private fun initList(){
        val call = RetrofitAPI.getApiService().getWeekTime()
        call.enqueue(object: Callback<List<TimeListData>> {
            override fun onResponse(call: Call<List<TimeListData>>, response: Response<List<TimeListData>>) {
                Toast.makeText(applicationContext, "Call Success", Toast.LENGTH_SHORT).show()
                if(response.isSuccessful) {
                    userTimeList = response.body() ?: listOf()
                    timeListAdapter.setList(userTimeList)
                }
            }

            override fun onFailure(call: Call<List<TimeListData>>, t: Throwable) {
                Toast.makeText(applicationContext, "Call Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}