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
    val profiles = listOf<Int>(1)// + (1..150)
    for (number in profiles) {
        profileWork = true
        launch(Dispatchers.Default) {
            stargateDiscordScript(number)
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
//unable
//1, 2, 3, 4