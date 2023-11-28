package ads_std

import github.forkGithub
import github.openGithub
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import java.io.File

const val CLOSE_PROFILE = false
const val CLOSE_PROFILE_IF_ERROR = false
const val WRITE_TO_ERROR_FILE = false

val error_file = File("src/main/kotlin/ads_std/error_profiles.txt")
val errorList = mutableListOf<Int>()
fun main(): Unit = runBlocking {
    ImagePath.add("src/main/kotlin/ads_std/png")
    ImagePath.add("src/main/kotlin/github/png")
    val list =
        listOf<Int>() +
                (201..225)
    val profiles = list.toMutableList()
    println("Profiles:\n$profiles")
    val freeWorkRegions = formWorkingRegions(
        2, 2, 1000, 820,
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
        //openGithub(workRegion)
        //forkGithub(workRegion)
        openUrlSikuliDark(workRegion.screen, "https://github.com/signup?source=login")
        workRegion.screen.wait(6.0)
        newTabSikuli(workRegion.screen)
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
