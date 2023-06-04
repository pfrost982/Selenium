package ads_std

import java.io.File

object BraavosAddress {
    private val address: List<String> = File("src/main/kotlin/ads_std/braavos_address.txt").useLines { it.toList() }
    fun getAddress(number: Int): String {
        return address[number - 1]
    }
}