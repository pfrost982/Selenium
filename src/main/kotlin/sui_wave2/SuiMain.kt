package sui_wave2

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

@Volatile
var profileWork = false

@Volatile
var isError = false
val file = File("src/main/kotlin/sui_wave2/error_list.txt")
fun main() = runBlocking {
    val profiles = listOf<Int>(1)// + (146..150)).toMutableSet()
    /*
        val errorList: List<String> = file.useLines { it.toList() }
        withMoneyList.forEach(){
            profiles.remove(it.toInt())
        }
    */
    val workList = profiles.toList()//.take(25)
    //val workList = errorList.stream().map { it.toInt() }.toList()
    println(workList)

    for (number in workList) {
        if (isError) {
            break
        }
        profileWork = true
        launch(Dispatchers.Default) {
            getNameService(number)
        }
        while (profileWork) {
            delay(1000)
        }
    }
    if (isError) {
        println("Work ended, with error!")
    } else {
        println("Work ended!")
    }
}