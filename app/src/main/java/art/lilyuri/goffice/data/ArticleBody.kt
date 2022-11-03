package art.lilyuri.goffice.data

data class ArticleBody(
    val articleName: String,
    val articleContent: String,
    val articleCategory: Int
)

data class ReadArticleBody(
    val articleIndex: Int
)
