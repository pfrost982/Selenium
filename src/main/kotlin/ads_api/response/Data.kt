package ads_api.response

data class Data(
    val status: String?,
    val debug_port: String,
    val webdriver: String,
    val ws: Ws
)