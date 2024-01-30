package discord

import java.io.File

object DiscordAccounts {
    private val tokens: List<String> = File("src/main/kotlin/discord/accounts.txt").useLines { it.toList() }
    fun getToken(number: Int): String {
        return tokens[number - 1]
    }
}