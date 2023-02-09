package ads_std

import java.io.File

object TwitLogins {
    private val logins: List<String> = File("src/main/kotlin/galxe_com/twit_logins.txt").useLines { it.toList() }
    fun getLogin(number: Int): String {
        return logins[number - 1]
    }
}