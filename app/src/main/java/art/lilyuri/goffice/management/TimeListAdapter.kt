package art.lilyuri.goffice.management

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import art.lilyuri.goffice.databinding.TimeListItemBinding

class TimeListAdapter: RecyclerView.Adapter<TimeListAdapter.MyView>() {
    private var userTimeList = listOf<TimeListData>()

    inner class MyView(private val binding: TimeListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pos: Int){
            binding.timeListName.text = userTimeList[pos].userName
            binding.timeListTotal.text = "40시간 중 "+userTimeList[pos].weekTime+"시간 근무"
            binding.TimeListProgress.progress = (userTimeList[pos].weekTime/40)*100
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView{
        val view = TimeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyView(view)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return userTimeList.size
    }

    fun setList(list: List<TimeListData>){
        userTimeList=list
    }
}