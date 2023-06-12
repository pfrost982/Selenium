package cap_monster

import ads_std.formWorkingRegions
import ads_std.queueCloseProfile
import ads_std.queueOpenProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.sikuli.script.ImagePath

var backgroundGreen = "\u001B[42m"
var backgroundBlack = "\u001B[40m"
val backgroundRed = "\u001B[41m"
val errorList = mutableListOf<Int>()
fun main(): Unit = runBlocking {
    ImagePath.add("src/main/kotlin/cap_monster/png")
    val list = listOf<Int>() + (1..150)
    val profiles = list.toMutableList()
    println("Profiles:\n$profiles")
    val freeWorkRegions = formWorkingRegions(
        3, 3, 0, 0, 690, 690, 10, 10,
        screenAdditionalWidth = 0
    )
    while (profiles.isNotEmpty()) {
        if (freeWorkRegions.isNotEmpty()) {
            val region = freeWorkRegions.removeFirst().apply {
                this.profile = profiles.removeFirst()
            }
            launch(Dispatchers.Default) {
                queueOpenProfile(region)
                addKeyScript(region)
                queueCloseProfile(region)
                freeWorkRegions.add(region)
                println(backgroundRed + "Error list:" + backgroundBlack)
                println(backgroundRed + errorList + backgroundBlack)
            }

        }
        delay(500)
    }
}