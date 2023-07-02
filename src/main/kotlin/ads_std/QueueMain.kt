package ads_std

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import metamask.changeLanguageToEnglish
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath

val errorList = mutableListOf<Int>()
fun main(): Unit = runBlocking {
    ImagePath.add("src/main/kotlin/ads_std/png")
    //
    val list =
        listOf<Int>(1)// +
                (201..350)
    val profiles = list.toMutableList()
    println("Profiles:\n$profiles")
    val freeWorkRegions = formWorkingRegions(
        1, 1, 6, 6, 1100, 900, 5, 5,
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
                //queueCloseProfile(region)
                freeWorkRegions.add(region)
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
    println(
        backgroundGreen + "Start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundBlack
    )
    try {
        changeLanguageToEnglish(workRegion)
    } catch (e: FindFailed) {
        println(backgroundRed + "Profile ${workRegion.profile} error")
        e.printStackTrace()
        workQueue.remove(screen)
        errorList.add(workRegion.profile)
    }
    println(
        backgroundGreen + "Finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundBlack
    )
}