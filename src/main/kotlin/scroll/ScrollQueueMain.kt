package scroll

import ads_std.formWorkingRegions
import ads_std.queueOpenProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.sikuli.script.ImagePath

val errorListQ = mutableListOf<Int>()
fun main(): Unit = runBlocking {
    ImagePath.add("src/main/kotlin/scroll/png")
    val list = listOf<Int>(1)// + (11..20)
    val profiles = list.toMutableList()
    println("Profiles:\n$profiles")
    val freeWorkRegions = formWorkingRegions(
        2, 2, 0, 0, 900, 1060,
        screenAdditionalWidth = 540
    )
    while (profiles.isNotEmpty()) {
        if (freeWorkRegions.isNotEmpty()) {
            val region = freeWorkRegions.removeFirst().apply {
                this.profile = profiles.removeFirst()
            }
            launch(Dispatchers.Default) {
                queueOpenProfile(region)
                poolOutScript(region.screen)
                //queueCloseProfileReleaseWorkRegion(region, freeWorkRegions)
            }
            println("Error list:\n$errorListQ")
        }
        delay(500)
    }
}