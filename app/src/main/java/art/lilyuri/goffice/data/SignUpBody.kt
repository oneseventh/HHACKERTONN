package art.lilyuri.goffice.data

data class SignUpBody(
    var email: String,
    var password: String,
    var company: String,
    var department: String,
    var position: String
)
