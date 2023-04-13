package robot_21_area_sui

import ads_std.fileToLinesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.sikuli.script.ImagePath
import org.sikuli.script.Screen
import java.io.File
import java.util.concurrent.ConcurrentLinkedQueue

val workQueue = ConcurrentLinkedQueue<WorkRegion>()
fun main(): Unit = runBlocking {
    ImagePath.add("src/main/kotlin/robot_21_area_sui/png")
    val file = File("src/main/kotlin/robot_21_area_sui/have2sui.txt")
    val have2SuiList = fileToLinesList(file)
    val workList = (1..150).toMutableList()
    have2SuiList.forEach {
        workList.remove(it.toInt())
    }
    val profiles = workList.take(21)
    println("Profiles:\n$profiles")
    val workRegions = mutableListOf<WorkRegion>()
    for (line in 1..3) {
        for (row in 1..5) {
            val screen = Screen()
            screen.w = 510
            screen.h = 700
            screen.x = 30 + (row - 1) * 510
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
        delay(5000)
    }
    launch(Dispatchers.Default) {
        while (true) {
            println("Queue size: ${workQueue.size}")
            delay(3000)
        }
    }
}

fun queueAddClickRelease(workRegion: WorkRegion) {
    workQueue.add(workRegion)
    while (workQueue.peek() != workRegion) {
        workRegion.screen.wait(0.01)
    }
    workRegion.screen.click()
    workQueue.poll()
}

fun queueAddClick(workRegion: WorkRegion) {
    workQueue.add(workRegion)
    while (workQueue.peek() != workRegion) {
        workRegion.screen.wait(0.01)
    }
    workRegion.screen.click()
}

fun queueClickRelease(workRegion: WorkRegion) {
    while (workQueue.peek() != workRegion) {
        workRegion.screen.wait(0.01)
    }
    workRegion.screen.click()
    workQueue.poll()
}

fun queueRelease(workRegion: WorkRegion) {
    while (workQueue.peek() != workRegion) {
        workRegion.screen.wait(0.01)
    }
    workQueue.poll()
}
