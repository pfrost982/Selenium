package anticaptcha_extension

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Volatile
var profileWork: Boolean = false

fun main() {
    runBlocking {

        val profiles = arrayOf(150)

        for (number in 1..3) {
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