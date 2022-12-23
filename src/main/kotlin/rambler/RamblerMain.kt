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
    val profiles = listOf<Int>(150)// + (138 .. 149)
    for (number in profiles) {
        if (isThereAreMistakes){
            break
        }
        profileWork = true
        launch(Dispatchers.Default) {
            ramblerScript(number)
        }
        while (profileWork) {
            delay(1000)
        }
    }
    if(isThereAreMistakes) {
        println("Work ended, with error!")
    } else {
        println("Work ended!")
    }
}