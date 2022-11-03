package art.lilyuri.goffice.management

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import art.lilyuri.goffice.R
import art.lilyuri.goffice.article.ArticleViewActivity
import art.lilyuri.goffice.article.TimeListView
import art.lilyuri.goffice.utils.ArticleData

class TimeListAdapter(private val context: Context): RecyclerView.Adapter<TimeListAdapter.ViewHolder>() {
    var datas = mutableListOf<TimeListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.boardview,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(oView: View) : RecyclerView.ViewHolder(oView) {

        private val name: TextView = itemView.findViewById(R.id.time_list_name)
        private val total: TextView = itemView.findViewById(R.id.time_list_total)

        fun bind(item: TimeListData) {
            name.text = item.userName
            total.text = item.weekTime.toString()
            itemView.setOnClickListener {
                Intent(context, TimeListView::class.java).apply {
                    putExtra("core", item.weekTime)
                    putExtra("name", item.userName)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }

        }
    }
}