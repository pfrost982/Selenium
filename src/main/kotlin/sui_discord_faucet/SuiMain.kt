package sui_discord_faucet

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Volatile
var profileWork = false
@Volatile
var isError = false

fun main() = runBlocking {
    val profiles = listOf<Int>(1)// + (1 .. 148)
    for (number in profiles) {
        if (isError){
            break
        }
        profileWork = true
        launch(Dispatchers.Default) {
            suiScript(number)
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