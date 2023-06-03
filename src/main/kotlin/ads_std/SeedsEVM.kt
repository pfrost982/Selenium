package ads_std

import java.io.File

object SeedsEVM {
    private val seeds: List<String> = File("src/main/kotlin/ads_std/seeds_evm.txt").useLines { it.toList() }
    fun getSeed(number: Int): String {
        return seeds[number - 1]
    }
}