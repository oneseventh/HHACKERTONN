package art.lilyuri.goffice.utils

data class ArticleData(
    val idx: Int, // 게시글 번호
    val articleCategory: Int,
    val articleName: String, // 게시글 이름
    val articleAuthor: String, // 게시글 작성자
    val articleContent: String, // 게시글 내용
    val articleComment: Int, // 게시글 댓글 갯수
    val articleDate: String // 게시글 작성 일자
) {
    override fun toString(): String {
        return "{\"index\": $idx, \"name\": \"$articleName\", \"author\": \"$articleAuthor\", \"content\": \"$articleContent\", \"created_at\": \"$articleDate\", \"comment\": $articleComment}"
    }
}

