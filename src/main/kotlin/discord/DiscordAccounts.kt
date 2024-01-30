package discord

import java.io.File

object DiscordAccounts {
    private val tokens: List<String> = File("src/main/kotlin/ads_std/accounts.txt").useLines { it.toList() }
    fun getToken(number: Int): String {
        return tokens[number - 1]
    }
}