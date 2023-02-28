package dune

import ads_std.Mails
import ads_std.TwitLogins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Volatile
var profileWork = false

@Volatile
var isError = false
val mails = Mails
val logins = TwitLogins

fun main() = runBlocking {
    val profiles = listOf<Int>(1)// + (146..150)
    for (number in profiles) {
        if (isError) {
            break
        }
        profileWork = true
        launch(Dispatchers.Default) {
            registrationScript(number)
        }
        while (profileWork) {
            delay(1000)
        }
    }
    if (isError) {
        println("Work ended, with error!")
    } else {
        println("Work ended!")
    }
}