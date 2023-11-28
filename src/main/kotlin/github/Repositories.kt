package github

import java.io.File

object Repositories {
    private val repositories: List<String> = File("src/main/kotlin/github/repositories.txt").useLines { it.toList() }
    fun getRepository(number: Int): String {
        return repositories[number - 1]
    }
}