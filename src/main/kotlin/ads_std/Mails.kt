package ads_std

import java.io.File

object Mails {
    private val logins: List<String> = File("src/main/kotlin/ads_std/rambler_emails.txt").useLines { it.toList() }
    fun getMail(number: Int): String {
        return logins[number - 1]
    }
}