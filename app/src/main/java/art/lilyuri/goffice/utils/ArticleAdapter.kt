package art.lilyuri.goffice.utils

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
            itemView.setOnClickListener {
                Intent(context, ArticleViewActivity::class.java).apply {
                    putExtra("idx", item.idx)
                    putExtra("name", item.articleName)
                    putExtra("comment", item.articleComment)
                    putExtra("content", item.articleContent)
                    putExtra("category", item.articleCategory)
                    putExtra("date", item.articleDate)
                    putExtra("author", item.articleAuthor)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }
        }
    }


}