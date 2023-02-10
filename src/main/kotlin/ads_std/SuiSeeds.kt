package ads_std

import java.io.File

object SuiSeeds {
    private val logins: List<String> = File("src/main/kotlin/ads_std/sui_seeds.txt").useLines { it.toList() }
    fun getSeed(number: Int): String {
        return logins[number - 1]
    }
}