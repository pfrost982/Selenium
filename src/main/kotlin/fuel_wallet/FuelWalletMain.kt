package fuel_wallet

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Volatile
var profileWork = false
@Volatile
var isError = false

fun main() = runBlocking {
    val profiles = listOf(1)// + (145 .. 150)
    for (number in profiles) {
        if (isError){
            break
        }
        profileWork = true
        launch(Dispatchers.Default) {
            fuelDiscordScript(number)
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