package fuel_wallet

import java.io.File

object Seeds {
    private val logins: List<String> = File("src/main/kotlin/fuel_wallet/seeds.txt").useLines { it.toList() }
    fun getSeed(number: Int): String {
        return logins[number - 1]
    }
}