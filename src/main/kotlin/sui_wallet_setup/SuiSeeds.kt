package sui_wallet_setup

import java.io.File

object SuiSeeds {
    private val logins: List<String> = File("src/main/kotlin/sui_wallet_setup/sui_seeds.txt").useLines { it.toList() }
    fun getSeed(number: Int): String {
        return logins[number - 1]
    }
}