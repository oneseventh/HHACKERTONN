package art.lilyuri.goffice.data

data class SignUpBody(
    var email: String,
    var password: String,
    var companyName: String,
    var department: String,
    var position: String,
    var name: String,
    var phone_number: String,
)
