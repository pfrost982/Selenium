package fuel_wallet

import java.io.File

object FuelSeeds {
    private val logins: List<String> = File("src/main/kotlin/fuel_wallet/fuel_seeds.txt").useLines { it.toList() }
    fun getSeed(number: Int): String {
        return logins[number - 1]
    }
}