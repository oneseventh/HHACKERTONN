package art.lilyuri.goffice

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import art.lilyuri.goffice.article.ArticleListActivity
import art.lilyuri.goffice.article.ArticleListAllActivity
import art.lilyuri.goffice.data.MsgData
import art.lilyuri.goffice.data.TimeData
import art.lilyuri.goffice.databinding.ActivityMainBinding
import art.lilyuri.goffice.management.TimeList
import art.lilyuri.goffice.retrofit.RetrofitAPI
import art.lilyuri.goffice.sharedpreferences.SharedPreferences
import art.lilyuri.goffice.management.AdminActivity
import art.lilyuri.goffice.utils.ArticleAdapter
import art.lilyuri.goffice.utils.ArticleData
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    lateinit var adapter: ArticleAdapter
    val datas = mutableListOf<ArticleData>()

    private val ment: List<String> = listOf("현재 이번주 연장 근무 시간은 0시간 입니다", "스트레칭은 필수!", "한국인은 밥심!!", "과도한 업무는 건강에 좋지 않아요!")

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainmain.istool.mainLayoutToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.mainNavigationView.setNavigationItemSelectedListener(this)

        binding.mainmain.istool.toolMenu.setOnClickListener {
            binding.mainDrawerLayout.openDrawer(GravityCompat.END)
        }
        val call = RetrofitAPI.getApiService().getCurrentStartedTime(SharedPreferences.prefs.getString("token","-1"))
        call.enqueue(object : Callback<TimeData> {
            override fun onResponse(call: Call<TimeData>, response: Response<TimeData>) {
                //binding.mainmain.todayText.text
                if(response.isSuccessful) {
                    val kext = response.body()!!.time.replace(".000Z","")
                    var time = LocalDateTime.parse(kext)
                    time = time.plusHours(9);
                    var dateStr = time.toString().split("T");
                    Log.d(">><<",dateStr.toString())
                    dateStr = dateStr.get(1).split(":")
                    val sibun = "출근 시간: "+dateStr[0] + "시 " + dateStr[1] + "분"
                    val exit = (dateStr[0].toInt() + 9) % 24
                    val sibunE = "예상 퇴근 시간: "+exit.toString() + "시 " + dateStr[1] + "분";
                    val cur = LocalDateTime.now()
                    val formatter_Si = DateTimeFormatter.ofPattern("HHmm")
                    val chulId =(dateStr[0]+dateStr[1]).toInt()
                    val formated = cur.format(formatter_Si).toString().toInt()
                    val progress = (((formated - chulId)) %
                            (900));
                    Log.d(">><<",progress.toString())
                    val t = getTimeString(cur.format(DateTimeFormatter.ofPattern("HH")).toString(), cur.format(DateTimeFormatter.ofPattern("mm")).toString(),exit.toString(),dateStr[1])
                    binding.mainmain.helloMsg.text = t + if(chulId < (exit.toString()+dateStr[1]).toInt()) "미달" else "초과";

                    binding.mainmain.pgMain.setProgress(progress)
                    binding.mainmain.todayText.text = sibun+"\n"+sibunE
                }

            }

            override fun onFailure(call: Call<TimeData>, t: Throwable) {

            }

        })


        binding.mainmain.workButton.setOnClickListener {
            Log.d(">","dsafsdf")
            //출근요청
            val call = RetrofitAPI.getApiService().startWork(SharedPreferences.prefs.getString("token","-1"))
            call.enqueue(object : Callback<MsgData> {
                override fun onResponse(call: Call<MsgData>, response: Response<MsgData>) {
                    if(response.isSuccessful) {
//                        binding.mainmain.beforeWork.visibility = View.INVISIBLE
//                        binding.mainmain.workButton.visibility = View.GONE
//                        binding.mainmain.processWork.visibility = View.VISIBLE
//                        binding.mainmain.escapeButton.visibility = View.VISIBLE
//                        binding.mainmain.restButton.visibility = View.VISIBLE
                        binding.mainmain.beforeWork.visibility = View.GONE
                        binding.mainmain.processWork.visibility = View.VISIBLE


                        val call22 = RetrofitAPI.getApiService().getCurrentStartedTime(SharedPreferences.prefs.getString("token","-1"))
                        call22.enqueue(object : Callback<TimeData> {
                            override fun onResponse(call: Call<TimeData>, response: Response<TimeData>) {
                                //binding.mainmain.todayText.text
                                if(response.isSuccessful) {
                                    val kext = response.body()!!.time.replace(".000Z","")
                                    var time = LocalDateTime.parse(kext)
                                    time = time.plusHours(9);
                                    var dateStr = time.toString().split("T");
                                    Log.d(">><<",dateStr.toString())
                                    dateStr = dateStr.get(1).split(":")
                                    val sibun = "출근 시간: "+dateStr[0] + "시 " + dateStr[1] + "분"
                                    val exit = (dateStr[0].toInt() + 9) % 24
                                    val sibunE = "예상 퇴근 시간: "+exit.toString() + "시 " + dateStr[1] + "분";
                                    val cur = LocalDateTime.now()
                                    val formatter_Si = DateTimeFormatter.ofPattern("HHmm")
                                    val chulId =(dateStr[0]+dateStr[1]).toInt()
                                    val formated = cur.format(formatter_Si).toString().toInt()
                                    val progress =(((formated - chulId) ) % (900));
                                    Log.d(">><<",progress.toString())
                                    val t = getTimeString(cur.format(DateTimeFormatter.ofPattern("HH")).toString(), cur.format(DateTimeFormatter.ofPattern("mm")).toString(),exit.toString(),dateStr[1])
                                    binding.mainmain.helloMsg.text = t + if(chulId < (exit.toString()+dateStr[1]).toInt()) "미달" else "초과";

                                    binding.mainmain.pgMain.setProgress(progress)
                                    binding.mainmain.todayText.text = sibun+"\n"+sibunE
                                }

                            }

                            override fun onFailure(call: Call<TimeData>, t: Throwable) {

                            }

                        })

                    } else {
                        Log.d(">","kkkk2")
                    }
                }

                override fun onFailure(call: Call<MsgData>, t: Throwable) {
                    Log.d(">","kkkk22")
                }

            })
        }

        binding.mainmain.escapeButton.setOnClickListener {
            val call = RetrofitAPI.getApiService().endWork(SharedPreferences.prefs.getString("token","-1"))
            call.enqueue(object : Callback<MsgData> {
                override fun onResponse(call: Call<MsgData>, response: Response<MsgData>) {
                    if(response.isSuccessful) {

//                        binding.mainmain.beforeWork.visibility = View.VISIBLE
//                        binding.mainmain.workButton.visibility = View.VISIBLE
//                        binding.mainmain.processWork.visibility = View.INVISIBLE
//                        binding.mainmain.escapeButton.visibility = View.GONE
//                        binding.mainmain.restButton.visibility = View.GONE

                        binding.mainmain.beforeWork.visibility = View.VISIBLE
                        binding.mainmain.processWork.visibility = View.GONE


                        val call33 = RetrofitAPI.getApiService().getCurrentStartedTime(SharedPreferences.prefs.getString("token","-1"))
                        call33.enqueue(object : Callback<TimeData> {
                            override fun onResponse(call: Call<TimeData>, response: Response<TimeData>) {
                                //binding.mainmain.todayText.text
                                if(response.isSuccessful) {
                                    val kext = response.body()!!.time.replace(".000Z","")
                                    var time = LocalDateTime.parse(kext)
                                    time = time.plusHours(9);
                                    var dateStr = time.toString().split("T");
                                    Log.d(">><<",dateStr.toString())
                                    dateStr = dateStr.get(1).split(":")
                                    val sibun = "출근 시간: "+dateStr[0] + "시 " + dateStr[1] + "분"
                                    val exit = (dateStr[0].toInt() + 9) % 24
                                    val sibunE = "예상 퇴근 시간: "+exit.toString() + "시 " + dateStr[1] + "분";
                                    val cur = LocalDateTime.now()
                                    val formatter_Si = DateTimeFormatter.ofPattern("HHmm")
                                    val chulId =(dateStr[0]+dateStr[1]).toInt()
                                    val formated = cur.format(formatter_Si).toString().toInt()
                                    val progress = (((formated - chulId)) % (900));
                                    Log.d(">><<",progress.toString())
                                    val t = getTimeString(cur.format(DateTimeFormatter.ofPattern("HH")).toString(), cur.format(DateTimeFormatter.ofPattern("mm")).toString(),exit.toString(),dateStr[1])
                                    binding.mainmain.helloMsg.text = t + if(chulId < (exit.toString()+dateStr[1]).toInt()) "미달" else "초과";

                                    binding.mainmain.pgMain.setProgress(progress)
                                    binding.mainmain.todayText.text = sibun+"\n"+sibunE
                                }

                            }

                            override fun onFailure(call: Call<TimeData>, t: Throwable) {

                            }

                        })
                    } else {
                        Log.d(">","kkkk1")
                    }
                }

                override fun onFailure(call: Call<MsgData>, t: Throwable) {
                    Log.d(">","kkkk11")
                }

            })

            //퇴근요청
        }





    }

    private fun getTimeString(curSi: String, curBun: String, destSi: String, destBun: String): String {
        val cur = curSi.toInt() * 60 + curBun.toInt()
        Log.d(">><<>><<", curSi.toString()+ " "+ curBun.toString())
        val dest = destSi.toInt() * 60 + destBun.toInt()
        val gap = dest - cur
        val gapSi = gap / 60;
        val gapBun = gap % 60;
        Log.d(">><<>><<",cur.toString() + " " + dest.toString())
        return gapSi.toString()+"시간 "+gapBun.toString()+"분 만큼"
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, ArticleListActivity::class.java)
        when(item.itemId){
            R.id.item1-> Toast.makeText(this,"account clicked",Toast.LENGTH_SHORT).show()
//            R.id.item2-> Chuno().test(this)
            R.id.item3-> startActivity(Intent(this, AdminActivity::class.java))
            R.id.item5 -> startActivity(intent)
        }
        return false
    }

    override fun onBackPressed() { //뒤로가기 처리
        if(binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.mainDrawerLayout.closeDrawers()
            // 테스트를 위해 뒤로가기 버튼시 Toast 메시지
            Toast.makeText(this,"back btn clicked", Toast.LENGTH_SHORT).show()
        } else{
            super.onBackPressed()
        }
    }

    private fun <T> List<T>.random() : T {
        val random = Random().nextInt((size))
        return get(random)
    }
}