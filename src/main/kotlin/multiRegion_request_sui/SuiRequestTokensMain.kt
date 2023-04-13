package multiRegion_request_sui

import ads_std.WorkRegion
import ads_std.fileToLinesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.sikuli.script.ImagePath
import org.sikuli.script.Screen
import java.io.File

fun main(): Unit = runBlocking {
    ImagePath.add("src/main/kotlin/multiRegion_request_sui/png")
    val file = File("src/main/kotlin/multiRegion_request_sui/have2sui.txt")
    val have2SuiList = fileToLinesList(file)
    val workList = (1..150).toMutableList()
    have2SuiList.forEach {
        workList.remove(it.toInt())
    }
    val profiles = workList.take(30)
    println("Profiles:\n$profiles")
    val workRegions = mutableListOf<WorkRegion>()
    for (line in 1..3) {
        for (row in 1..5) {
            val screen = Screen()
            screen.w = 500
            screen.h = 700
            screen.x = 16 + (row - 1) * 516
            screen.y = (line - 1) * 700
            val workRegion = WorkRegion(line, row, screen, 0)
            workRegions.add(workRegion)
        }
    }
    val zip = workRegions.zip(profiles)
    println(zip.size)
    zip.forEach {
        it.first.profile = it.second
        launch(Dispatchers.Default) {
            requestTokenClicker(it.first)
        }
    }
}

