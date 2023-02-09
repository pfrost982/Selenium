package ads_std

import java.io.File

object SeedsEVM {
    private val logins: List<String> = File("src/main/kotlin/keplr_wallet_setup/seeds_evm.txt").useLines { it.toList() }
    fun getSeed(number: Int): String {
        return logins[number - 1]
    }
}