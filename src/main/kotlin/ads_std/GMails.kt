package ads_std

import java.io.File

object GMails {
    private val logins: List<String> = File("src/main/kotlin/ads_std/gmails.txt").useLines { it.toList() }
    fun getGMail(number: Int): String {
        return logins[number - 1]
    }
}