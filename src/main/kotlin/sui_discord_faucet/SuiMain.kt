package sui_discord_faucet

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

@Volatile
var profileWork = false
@Volatile
var isError = false
val file = File("src/main/kotlin/sui_discord_faucet/with_money.txt")
fun main() = runBlocking {
    val profiles = listOf<Int>(9)// + (147..150)).toMutableSet()
    val withMoneyList: List<String> = file.useLines { it.toList() }
/*
    withMoneyList.forEach(){
        profiles.remove(it.toInt())
    }
*/
    val workList = profiles.toList()//.take(25)
    println(workList)

    for (number in workList) {
        if (isError){
            break
        }
        profileWork = true
        launch(Dispatchers.Default) {
            //getSuiScript(number)
            //stakeSuiScript(number)
            getCapyScript(number)
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