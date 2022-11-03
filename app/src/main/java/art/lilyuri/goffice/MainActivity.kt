package art.lilyuri.goffice

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import art.lilyuri.goffice.article.ArticleListAllActivity
import art.lilyuri.goffice.databinding.ActivityMainBinding
import art.lilyuri.goffice.management.TimeList
import art.lilyuri.goffice.utils.ArticleAdapter
import art.lilyuri.goffice.utils.ArticleData
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
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



    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, ArticleListAllActivity::class.java)
        when(item.itemId){
            R.id.item1-> Toast.makeText(this,"account clicked",Toast.LENGTH_SHORT).show()
//            R.id.item2-> Chuno().test(this)
            R.id.item3-> startActivity(Intent(this, TimeList::class.java))
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