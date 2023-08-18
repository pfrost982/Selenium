package ads_std

import cap_monster.addKeyAndSetRepeat
import debank.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath

val errorList = mutableListOf<Int>()
fun main(): Unit = runBlocking {
    ImagePath.add("src/main/kotlin/ads_std/png")
    ImagePath.add("src/main/kotlin/debank/png")
    ImagePath.add("src/main/kotlin/cap_monster/png")
    //
    val list =
        listOf<Int>(105)// +
                (81..100)
    val profiles = list.toMutableList()
    println("Profiles:\n$profiles")
    val freeWorkRegions = formWorkingRegions(
        2, 1, 5, 5, 990, 900, 5, 5,
        screenAdditionalWidth = 600
    )
    while (profiles.isNotEmpty()) {
        if (freeWorkRegions.isNotEmpty()) {
            val region = freeWorkRegions.removeFirst().apply {
                this.profile = profiles.removeFirst()
            }
            launch(Dispatchers.Default) {
                queueOpenProfile(region)
                script(region)
/*
                if (region.profile !in errorList) {
                    queueCloseProfile(region)
                }
*/
                freeWorkRegions.add(region)
                println(foregroundRed + "Error list:" + foregroundBlack)
                println(foregroundRed + errorList + foregroundBlack)
                println(workQueue)
            }
        }
        delay(500)
    }
}

suspend fun script(workRegion: WorkRegion) {
    val screen = workRegion.screen
    println(
        foregroundGreen + "Start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + foregroundBlack
    )
    try {
        addKeyAndSetRepeat(workRegion)
        registerRabby(workRegion)
        //openRabby(workRegion)
        swapRabby(workRegion)
        mintRabby(workRegion)
        requestTokenRabby(workRegion)
    } catch (e: FindFailed) {
        println(foregroundRed + "Profile ${workRegion.profile} error")
        e.printStackTrace()
        workQueue.remove(screen)
        errorList.add(workRegion.profile)
    }
    if (workRegion.profile in errorList) {
        println(
            foregroundRed + "Finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                    + foregroundBlack
        )
    } else {
        println(
            foregroundGreen + "Finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                    + foregroundBlack
        )
    }
}