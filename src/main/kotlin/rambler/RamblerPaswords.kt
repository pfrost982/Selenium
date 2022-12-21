package rambler

import java.io.File

object RamblerPaswords {
    private val logins: List<String> = File("src/main/kotlin/rambler/rambler_passwords.txt").useLines { it.toList() }
    fun getPassword(number: Int): String {
        return logins[number - 1]
    }
}