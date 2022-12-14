import galxe_com.galxeScript
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Volatile
var profileWork: Boolean = false
fun main() {
    runBlocking {
        for (number in 9..148) {
            profileWork = true
            launch(Dispatchers.Default) {
                galxeScript(8)
            }
            while (profileWork) {
                delay(1000)
            }
        }
    }
}