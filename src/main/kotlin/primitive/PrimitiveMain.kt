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
    val profiles = listOf<Int>() + (4 .. 150)
    for (number in profiles) {
        if (isError){
            break
        }
        profileWork = true
        launch(Dispatchers.Default) {
            primitiveScript(number)
        }
        while (profileWork) {
            delay(1000)
        }
    }
    if(isError) {
        println("Work ended, with error!")
    } else {
        println("Work ended!")
    }
}