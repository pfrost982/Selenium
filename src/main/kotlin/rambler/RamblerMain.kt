package rambler

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Volatile
var profileWork = false
@Volatile
var isThereAreMistakes = false

fun main() = runBlocking {

    val profiles = arrayOf(13)

    for (number in profiles) {
        if (isThereAreMistakes){
            println("Error detected!")
            return@runBlocking
        }
        profileWork = true
        launch(Dispatchers.Default) {
            ramblerScript(number)
        }
        while (profileWork) {
            delay(1000)
        }
    }
    println("Work ended!")
}