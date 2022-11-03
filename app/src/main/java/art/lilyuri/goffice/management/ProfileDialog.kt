package art.lilyuri.goffice

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import art.lilyuri.goffice.databinding.DialogEditInfoFormBinding

class ProfileDialog(context: Context, private val okCallback: (String) -> Unit,): Dialog(context) {
    private lateinit var dialogBinding : DialogEditInfoFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogBinding = DialogEditInfoFormBinding.inflate(layoutInflater)
        setContentView(dialogBinding.root)
        initView()
    }

    private fun initView() = with(dialogBinding){
        setCancelable(false)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        editOkBtn.setOnClickListener{
            if (!dialogBinding.etNameEdit.text.isEmpty())
                okCallback(etNameEdit.text.toString())
            else if (!dialogBinding.etCompanyEdit.text.isEmpty())
                okCallback(etCompanyEdit.text.toString())
            else if (!dialogBinding.etEmailEdit.text.isEmpty())
                okCallback(etEmailEdit.text.toString())
            else if (!dialogBinding.etTeamEdit.text.isEmpty())
                okCallback(etTeamEdit.text.toString())
            else if (!dialogBinding.etDepartmentEdit.text.isEmpty())
                okCallback(etDepartmentEdit.text.toString())

            dismiss()
        }

    }
}