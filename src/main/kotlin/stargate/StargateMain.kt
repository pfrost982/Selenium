package stargate

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
    val profiles = listOf<Int>()// + (1..150)
    for (number in profiles) {
        profileWork = true
        launch(Dispatchers.Default) {
            stargateGuildScript(number)
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
//!veStaker
//5, 11, 12, 27, 33, 34, 35, 36, 40, 41, 43, 44, 47, 53, 54, 58, 65, 66, 71, 73, 75, 79, 86, 88, 89, 114, 125
