package ads_api.request

data class AdsRequest(
    val user_id: String,
    val user_proxy_config: UserProxyConfig
)
