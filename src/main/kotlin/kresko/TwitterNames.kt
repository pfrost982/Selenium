package kresko

import java.io.File

object TwitterNames {
    private val logins: List<String> = File("src/main/kotlin/kresko/twitter_names.txt").useLines { it.toList() }
    fun getName(number: Int): String {
        return logins[number - 1]
    }
}