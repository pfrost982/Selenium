package ads_std

import java.io.File

object PrivateKeysEVM {
    private val keys: List<String> = File("src/main/kotlin/ads_std/private_key_evm.txt").useLines { it.toList() }
    fun getKey(number: Int): String {
        return keys[number - 1]
    }
}