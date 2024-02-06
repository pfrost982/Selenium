package discord

import org.sikuli.script.Offset
import java.io.File

object DiscordAccounts {
    private val accounts: List<String> = File("src/main/kotlin/discord/accounts.txt").useLines { it.toList() }
    fun getMail(offset: Int, number: Int): String {
        return accounts[number - offset - 1].split(":")[0]
    }
    fun getPassword(offset: Int, number: Int): String {
        return accounts[number - offset  - 1].split(":")[1]
    }
    fun getToken(offset: Int, number: Int): String {
        return accounts[number - offset  - 1].split(":").last()
    }
}