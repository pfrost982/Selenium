package ads_std

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import linea.hopPool
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath

val errorList = mutableListOf<Int>()
//val seeds_file = File("src/main/kotlin/starknet/braavos_seeds.txt")
fun main(): Unit = runBlocking {
    ImagePath.add("src/main/kotlin/ads_std/png")
    ImagePath.add("src/main/kotlin/linea/png")
    val list = listOf<Int>() +
    (135..150)
    val profiles = list.toMutableList()
    println("Profiles:\n$profiles")
    val freeWorkRegions = formWorkingRegions(
        3, 2, 10, 0, 690, 690, 0, 5,
        screenAdditionalWidth = 340
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
        hopPool(screen)
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