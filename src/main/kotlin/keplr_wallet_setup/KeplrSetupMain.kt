package keplr_wallet_setup

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Volatile
var profileWork: Boolean = false

fun main() {
    runBlocking {

        //val profiles = arrayOf(5)

        for (number in 6..100) {
            profileWork = true
            launch(Dispatchers.Default) {
                keplrSetupScript(number)
            }
            while (profileWork) {
                delay(1000)
            }
        }
        println("Work ended!")
    }
}