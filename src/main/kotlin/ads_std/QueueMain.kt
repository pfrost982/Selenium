package ads_std

import kotlinx.coroutines.Dispatchers
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

const val CLOSE_PROFILE = false
const val CLOSE_PROFILE_IF_ERROR = false
const val WRITE_TO_ERROR_FILE = false

const val ADD_RANGE = true
const val START_PROFILE = 301
const val END_PROFILE = 350

val error_file = File("src/main/kotlin/ads_std/error_profiles.txt")
val errorList = ConcurrentLinkedQueue<Int>()
suspend fun main() = coroutineScope {
    ImagePath.add("src/main/kotlin/ads_std/png")
    val profiles = mutableListOf<Int>()
    if (ADD_RANGE) {
        profiles.addAll(START_PROFILE..END_PROFILE)
    }
    println("Profiles:\n$profiles")
    val workRegions = formWorkingRegions(
        LINES, ROWS, WIDTH, HEIGHT,
        screenAdditionalWidth = ADDITIONAL_WIDTH
    )
    val freeWorkRegionsChanel = Channel<WorkRegion>()
    launch { workRegions.forEach { freeWorkRegionsChanel.send(it) } }
    for (profile in profiles) {
        val region = freeWorkRegionsChanel.receive().apply { this.profile = profile }
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
            println(foregroundRed + "Error list:\n$errorList" + foregroundBlack)
            println("Work queue:\n$workQueue")
            if (profile == profiles.last()) {
                freeWorkRegionsChanel.close()
                println("Chanel closed...")
            } else {
                freeWorkRegionsChanel.send(region)
            }
        }
    }
}

suspend fun script(workRegion: WorkRegion) {
    val screen = workRegion.screen
    workRegion.println(
        "Start, line: ${workRegion.line}, row: ${workRegion.row}", foregroundGreen
    )
    try {
        screen.wait(2.0)
        openUrlSikuliDark(screen, "twitter.com")
    } catch (e: FindFailed) {
        workRegion.println("Error", foregroundRed)
        e.printStackTrace()
        workQueue.remove(screen)
        errorList.add(workRegion.profile)
    }
    if (workRegion.profile in errorList) {
        workRegion.println(
            "Finish with error, line: ${workRegion.line}, row: ${workRegion.row}", foregroundRed
        )
    } else {
        workRegion.println(
            "Finish, line: ${workRegion.line}, row: ${workRegion.row}", foregroundGreen
        )
    }
}
