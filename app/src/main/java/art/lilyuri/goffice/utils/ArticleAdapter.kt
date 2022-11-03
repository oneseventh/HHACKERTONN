package art.lilyuri.goffice.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import art.lilyuri.goffice.R

class ArticleAdapter(private val context: Context) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    var datas = mutableListOf<ArticleData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.boardview,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name: TextView = itemView.findViewById(R.id.article_name)
        private val author: TextView = itemView.findViewById(R.id.article_author)
        private val comment: TextView = itemView.findViewById(R.id.article_vote)

        @SuppressLint("SetTextI18n")
        fun bind(item: ArticleData) {
            name.text = item.articleName
            author.text = "\uD83D\uDC68 ${item.articleAuthor}"
            comment.text = "\uD83D\uDCAC 댓글 ${item.articleComment}개"
//            Glide.with(itemView).load(item.img).into(imgProfile)

        }
    }


}