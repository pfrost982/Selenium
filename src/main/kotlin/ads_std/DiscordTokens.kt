package ads_std

import java.io.File

object DiscordTokens {
    private val tokens: List<String> = File("src/main/kotlin/ads_std/discord_tokens.txt").useLines { it.toList() }
    fun getToken(number: Int): String {
        return tokens[number - 1]
    }
}