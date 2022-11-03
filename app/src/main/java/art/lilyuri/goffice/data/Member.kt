package art.lilyuri.goffice.data

data class Member(
    var id: Int,
    var email: String,
    var phone_nubmer: String,
    var name: String,
    var password: String,
    var position: String,
    var company_id: Int,
    var department: String,
    var createdAt: String,
    var updatedAt: String
)
