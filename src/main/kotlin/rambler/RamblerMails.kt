package rambler

import java.io.File

object RamblerMails {
    private val logins: List<String> = File("src/main/kotlin/rambler/rambler_emails.txt").useLines { it.toList() }
    fun getMail(number: Int): String {
        return logins[number - 1]
    }
}