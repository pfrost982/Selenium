package ads_std

import java.io.File

object StarknetSeeds {
    private val seeds: List<String> = File("src/main/kotlin/ads_std/argentx_braavos_seeds.txt").useLines { it.toList() }
    fun getSeed(number: Int): String {
        return seeds[number - 1]
    }
}