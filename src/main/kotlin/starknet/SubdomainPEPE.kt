package starknet

import java.io.File

object SubdomainPEPE {
    private val logins: List<String> = File("src/main/kotlin/starknet/subdomainPEPE.txt").useLines { it.toList() }
    fun getSubdomain(number: Int): String {
        return logins[number - 1]
    }
}