package ads_std

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import starknet.starkPEPEArgentX
import java.io.File
import java.util.concurrent.ConcurrentLinkedQueue

const val CLOSE_PROFILE = false
const val CLOSE_PROFILE_IF_ERROR = false
const val WRITE_TO_ERROR_FILE = false

val error_file = File("src/main/kotlin/ads_std/error_profiles.txt")
val errorList = ConcurrentLinkedQueue<Int>()
fun main(): Unit = runBlocking {
    ImagePath.add("src/main/kotlin/ads_std/png")
    ImagePath.add("src/main/kotlin/starknet/png")
    ImagePath.add("src/main/kotlin/starknet/png2")
    val profiles =
        listOf<Int>(2)// +
    (1..50)
    println("Profiles:\n$profiles")
    val freeWorkRegions = formWorkingRegions(
        3, 1, 1000, 700,
        screenAdditionalWidth = 590
    )
    for (profile in profiles) {
        while (freeWorkRegions.isEmpty()) {
            delay(500)
        }
        val region = freeWorkRegions.poll().apply {
            this.profile = profile
        }
        launch(Dispatchers.Default) {
            queueOpenProfile(region)
            script(region)
            if (region.profile !in errorList) {
                if (CLOSE_PROFILE) {
                    queueCloseProfile(region)
                }
            } else {
                if (WRITE_TO_ERROR_FILE) {
                    fileAppendString(error_file, "${region.profile}")
                }
                if (CLOSE_PROFILE_IF_ERROR) {
                    queueCloseProfile(region)
                }
            }
            freeWorkRegions.add(region)
            println(foregroundRed + "Error list:" + foregroundBlack)
            println(foregroundRed + errorList + foregroundBlack)
            println("Work queue:")
            println(workQueue)
        }
    }
}

suspend fun script(workRegion: WorkRegion) {
    val screen = workRegion.screen
    println(
        foregroundGreen + "Start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + foregroundBlack
    )
    try {
        starkPEPEArgentX(workRegion)
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
