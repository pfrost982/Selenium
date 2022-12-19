package keplr_wallet_setup

import java.io.File

object KeplrSeeds {
    private val logins: List<String> = File("src/main/kotlin/keplr_wallet_setup/keplr_seeds.txt").useLines { it.toList() }
    fun getSeed(number: Int): String {
        return logins[number - 1]
    }
}