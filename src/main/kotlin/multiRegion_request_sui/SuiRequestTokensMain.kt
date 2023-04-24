package multiRegion_request_sui

import ads_std.fileToLinesList
import ads_std.formWorkingRegions
import ads_std.queueCloseProfileReleaseWorkRegion
import ads_std.queueOpenProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.sikuli.script.ImagePath
import java.io.File

fun main(): Unit = runBlocking {
    ImagePath.add("src/main/kotlin/multiRegion_request_sui/png")
    val file = File("src/main/kotlin/multiRegion_request_sui/have2sui.txt")
    val have2SuiList = fileToLinesList(file)
    val workList = (1..150).toMutableList()
    have2SuiList.forEach {
        workList.remove(it.toInt())
    }
    val profiles = workList.take(15).toMutableList()
    println("Profiles:\n$profiles")
    val freeWorkRegions = formWorkingRegions(3, 5, 16, 0, 500, 700, 16)
    while (profiles.isNotEmpty()) {
        if (freeWorkRegions.isNotEmpty()) {
            val region = freeWorkRegions.removeFirst().apply {
                this.profile = profiles.removeFirst()
            }
            launch(Dispatchers.Default) {
                queueOpenProfile(region)
                requestTokenClicker(region)
                queueCloseProfileReleaseWorkRegion(region, freeWorkRegions)
            }
        }
        delay(500)
    }
}

