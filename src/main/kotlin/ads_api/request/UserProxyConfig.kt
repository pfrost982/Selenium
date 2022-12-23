package ads_api.request

data class UserProxyConfig(
    val proxy_host: String,
    val proxy_port: String,
    val proxy_user: String,
    val proxy_password: String,
    val proxy_type: String = "socks5"
)