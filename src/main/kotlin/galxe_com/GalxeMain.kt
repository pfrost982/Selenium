package galxe_com

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Volatile
var profileWork: Boolean = false
fun main() {
    runBlocking {
        for (number in 1..148) {
            profileWork = true
            launch(Dispatchers.Default) {
                galxeScript(number)
            }
            while (profileWork) {
                delay(1000)
            }
        }
    }
}