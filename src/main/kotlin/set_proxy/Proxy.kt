package set_proxy

import java.io.File

object Proxy {
    private val proxyList: List<String> = File("src/main/kotlin/set_proxy/proxy.txt").useLines { it.toList() }
    fun getProxy(number: Int): String {
        return proxyList[number - 1]
    }
}