package robot_21_area_sui

import ads_std.WALLET_PASS
import kotlinx.coroutines.runBlocking
import org.sikuli.script.Key
import org.sikuli.script.Pattern
import org.sikuli.script.Screen

fun main() = runBlocking {
    val screen = Screen()
    screen.w = 510
    screen.h = 700
    while (true) {
        for (line in 1..3) {
            for (row in 1..7) {
                println("line: $line, row: $row")
                screen.x = 100 + (row - 1) * 510
                screen.y = (line - 1) * 700
                println(screen.bounds)
                val walletNotOpen = screen.exists(
                    Pattern("favorites_and_extensions.png")
                        .targetOffset(20, 0)
                        .similar(0.95)
                )
                if (walletNotOpen != null) {
                    println("Open wallet")
                    screen.click()
                    screen.wait("sui_wallet.png")
                    screen.click()
                    val password = screen.exists("password.png")
                    if (password != null) {
                        println("Unlock wallet")
                        screen.click()
                        screen.paste(WALLET_PASS)
                        screen.type(Key.ENTER)
                    } else {
                        println("Wallet unlocked")
                    }
                } else {
                    println("Wallet opened")
                }
                val requestTokens = screen.exists("request_tokens.png")
                if (requestTokens != null) {
                    println("Request tokens")
                    screen.click()
                } else {
                    println("Request button not founded")
                }
            }
        }
        println("Circle ended, pause...")
        screen.wait(30.0)
    }
}
