package set_proxy

import ads_api.AdsApiStore
import ads_api.request.AdsRequest
import ads_api.request.UserProxyConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val profiles = listOf<Int>(2)// + (2..150)
    for (number in profiles) {

        val proxyIndex =
            if ((number % 50) > 0) {
                number % 50
            } else {
                50
            }

        val proxy = Proxy.getProxy(proxyIndex).split(":")
        println("setup profile $number proxy: $proxy  -- $proxyIndex")
        val userId = ads_std.profiles[number - 1]
        val request = AdsRequest(userId, UserProxyConfig(proxy[0], proxy[1], proxy[2], proxy[3]))
        val response = AdsApiStore.api.setProxy(request)
        println(response.msg)
        delay(3000)
    }
}