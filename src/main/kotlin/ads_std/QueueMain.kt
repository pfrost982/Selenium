package ads_std

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import java.io.File
import java.util.concurrent.ConcurrentLinkedQueue

const val LINES = 1
const val ROWS = 1
const val WIDTH = 1100
const val HEIGHT = 900
const val ADDITIONAL_WIDTH = 0

const val CLOSE_PROFILE = true
const val CLOSE_PROFILE_IF_ERROR = false
const val WRITE_TO_ERROR_FILE = false

const val ADD_RANGE = false
const val START_PROFILE = 1
const val END_PROFILE = 2

val error_file = File("src/main/kotlin/ads_std/error_profiles.txt")
val errorList = ConcurrentLinkedQueue<Int>()
suspend fun main() = coroutineScope {
    ImagePath.add("src/main/kotlin/ads_std/png")
    val profiles = mutableListOf<Int>(2)
    if (ADD_RANGE) {
        profiles.addAll(START_PROFILE..END_PROFILE)
    }
    println("Profiles:\n$profiles")
    val freeWorkRegions = Channel<WorkRegion>()
    launch {
        formWorkingRegions(
            LINES, ROWS, WIDTH, HEIGHT,
            screenAdditionalWidth = ADDITIONAL_WIDTH
        ).forEach {
            freeWorkRegions.send(it)
        }
    }

    for (profile in profiles) {
        val region = freeWorkRegions.receive().apply {
            this.profile = profile
            println("New free work region: $this", foregroundGreen)
        }
        launch {
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
            freeWorkRegions.send(region)
            region.println("Error list:\n$errorList", foregroundRed)
            println("Work queue:\n$workQueue")
        }
    }
    return@coroutineScope
}

suspend fun script(workRegion: WorkRegion) {
    val screen = workRegion.screen
    workRegion.println(
        "Start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}", foregroundGreen
    )
    try {
        screen.wait(2.0)
        openUrlSikuliDark(screen, "twitter.com")
        screen.wait("ads_account_id.png")
    } catch (e: FindFailed) {
        println(foregroundRed + "Profile ${workRegion.profile} error")
        e.printStackTrace()
        workQueue.remove(screen)
        errorList.add(workRegion.profile)
    }
    if (workRegion.profile in errorList) {
        workRegion.println(
            "Finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}", foregroundRed
        )
    } else {
        workRegion.println(
            "Finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}", foregroundGreen
        )
    }
}
