package ads_std

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import starknet.daiToEth

val errorList = mutableListOf<Int>()
//val seeds_file = File("src/main/kotlin/starknet/braavos_seeds.txt")
fun main(): Unit = runBlocking {
    ImagePath.add("src/main/kotlin/starknet/png")
    val list = listOf<Int>()// +
    (1..150)
    val profiles = list.toMutableList()
    println("Profiles:\n$profiles")
    val freeWorkRegions = formWorkingRegions(
        3, 3, 10, 0, 690, 690, 6, 6,
        screenAdditionalWidth = 0
    )
    while (profiles.isNotEmpty()) {
        if (freeWorkRegions.isNotEmpty()) {
            val region = freeWorkRegions.removeFirst().apply {
                this.profile = profiles.removeFirst()
            }
            launch(Dispatchers.Default) {
                queueOpenProfile(region)
                script(region)
                queueCloseProfileReleaseWorkRegion(region, freeWorkRegions)
                //freeWorkRegions.add(region)
                println(backgroundRed + "Error list:" + backgroundBlack)
                println(backgroundRed + errorList + backgroundBlack)
                println(workQueue)
            }
        }
        delay(500)
    }
}

suspend fun script(workRegion: WorkRegion) {
    val screen = workRegion.screen
    var color = backgroundGreen
    println(
        color + "Start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundBlack
    )
    try {
        daiToEth(screen)
    } catch (e: FindFailed) {
        color = backgroundRed
        println(color + "Profile ${workRegion.profile} error")
        e.printStackTrace()
        workQueue.remove(screen)
        errorList.add(workRegion.profile)
    }
    println(
        color + "Finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundBlack
    )
}