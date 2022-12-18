package sui_wallet_setup

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Volatile
var profileWork: Boolean = false

fun main() {
    runBlocking {

        val profiles = arrayOf(1)

        for (number in profiles) {
            profileWork = true
            launch(Dispatchers.Default) {
                antiCaptcha(number)
            }
            while (profileWork) {
                delay(1000)
            }
        }
        println("Work ended!")
    }
}