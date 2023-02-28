package primitive

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Volatile
var profileWork = false

@Volatile
var isError = false

fun main() = runBlocking {
    val errorList = mutableListOf<Int>()
    val profiles = listOf<Int>(51)// + (1 .. 150)
    for (number in profiles) {
        profileWork = true
        launch(Dispatchers.Default) {
            discordRegistrationScript(number)
        }
        while (profileWork) {
            delay(1000)
        }
        if (isError) {
            errorList.add(number)
            println("Script number: $number ends with error")
            println(errorList)
            isError = false
        }
    }
    println("Work ended!")
    println("Error list: $errorList")
}