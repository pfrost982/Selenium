package scroll

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Volatile
var profileWork = false

@Volatile
var isError = false

val errorList = mutableListOf<Int>()

fun main() = runBlocking {
    val profiles = listOf<Int>(1)// + (7..130)
    for (number in profiles) {
/*
        if (isError) {
            break
        }
*/
        profileWork = true
        launch(Dispatchers.Default) {
            //scrollScript(number)
            //scrollGuildScript(number)
            scrollBridgeScript(number)
        }
        while (profileWork) {
            delay(1000)
        }
        println("Error list: $errorList")
    }
    if (isError) {
        println("Work ended, with error!")
    } else {
        println("Work ended!")
    }
}