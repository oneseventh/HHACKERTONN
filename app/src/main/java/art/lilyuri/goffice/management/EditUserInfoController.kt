package art.lilyuri.goffice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import art.lilyuri.goffice.databinding.EditUserInfoBinding

class EditUserInfoController : AppCompatActivity() {

    private val editBinding : EditUserInfoBinding by lazy { EditUserInfoBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(editBinding.root)

        editBinding.editInfoBtn.setOnClickListener {
            showProfileDialog()
        }
    }


    private fun showProfileDialog(){
        ProfileDialog(this){
        }.show()
    }
}