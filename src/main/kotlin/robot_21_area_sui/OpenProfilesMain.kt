package robot_21_area_sui

import ads_std.openProfileWithoutDriver
import kotlinx.coroutines.runBlocking
import org.sikuli.script.ImagePath
import java.io.File

fun main() = runBlocking {
    ImagePath.add("src/main/kotlin/robot_21_area_sui/png")
    val file = File("src/main/kotlin/robot_21_area_sui/have2sui.txt")
    val have2SuiList = file.useLines { it.toList() }
    val workList = (1..150).toMutableList()
    have2SuiList.forEach(){
        workList.remove(it.toInt())
    }
    val profiles = workList.take(21)
    println("Open profiles:")
    println(profiles)
    for (number in profiles) {
        openProfileWithoutDriver(number, 0, 0, 500, 200)
    }
    println("\nAll profiles opened")
}
