package art.lilyuri.goffice.utils

data class CompanyBody(
    val companyName: String,
    val companyCore: Int,
    val companyType: Int
)

data class CompanyData(
    val idx: Int,
    val companyName: String,
    val companyCore: Int,
    val companyType: Int
)

enum class CompanyType(val value: Int) {
    탄력적출퇴근제(2),
    선택적출퇴근제(1),
    시차출퇴근제(3)
}
