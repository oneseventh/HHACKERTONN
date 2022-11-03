package art.lilyuri.goffice.data

data class PostDT(
    var id: Int,
    var title: String,
    var content: String,
    var field: String,
    var createdAt: String,
    var updatedAt: String,
    var user_id: Int,
    var company_id: Int
)
