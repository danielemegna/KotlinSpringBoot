package usecase

data class SpringRequest(
    val pathVariables: Map<String, String>,
    val requestParams: Map<String, String>,
    val method: String,
    val headers: Map<String, String>
)