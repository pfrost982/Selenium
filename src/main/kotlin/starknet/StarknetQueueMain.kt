package starknet

import ads_std.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.sikuli.script.ImagePath
import java.io.File

val errorList = mutableListOf<Int>()
val seeds_file = File("src/main/kotlin/starknet/braavos_seeds.txt")
val address_file = File("src/main/kotlin/starknet/braavos_address.txt")
fun main(): Unit = runBlocking {
    ImagePath.add("src/main/kotlin/starknet/png")
    val list = listOf<Int>() +
            (143..150)
    val profiles = list.toMutableList()
    println("Profiles:\n$profiles")
    val freeWorkRegions = formWorkingRegions(
        3, 4, 10, 0, 690, 690, 10, 10,
        screenAdditionalWidth = 0
    )
    while (profiles.isNotEmpty()) {
        if (freeWorkRegions.isNotEmpty()) {
            val region = freeWorkRegions.removeFirst().apply {
                this.profile = profiles.removeFirst()
            }
            launch(Dispatchers.Default) {
                queueOpenProfile(region)
                setupBraavosWalletScript(region)
                //queueCloseProfileReleaseWorkRegion(region, freeWorkRegions)
                //freeWorkRegions.add(region)
                println(backgroundRed + "Error list:" + backgroundBlack)
                println(backgroundRed + errorList + backgroundBlack)
                println(workQueue)
            }
        }
        delay(500)
    }
}