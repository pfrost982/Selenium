package anticaptcha_extension

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Volatile
var profileWork: Boolean = false

fun main() {
    runBlocking {

        val profiles = arrayOf(51, 52)

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