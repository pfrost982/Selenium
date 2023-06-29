package sui_wave2

import ads_std.WALLET_PASS
import org.sikuli.script.Pattern
import org.sikuli.script.Screen

suspend fun stakeSuiScript(number: Int) {
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    screen.wait("extensions.png")
    screen.click()
    screen.wait("sui_icon.png")
    screen.click()
    screen.wait(Pattern("enter_password.png").targetOffset(0, 40))
    screen.click()
    screen.paste(WALLET_PASS)
    screen.wait("unlock_button.png")
    screen.click()
    screen.wait("stake_button.png", 24.0)
    screen.click()
    screen.wait("nodes_guru_button.png", 24.0)
    screen.click()
    screen.wait("select_amount_button.png")
    screen.click()
    screen.wait(Pattern("enter_amount.png").similar(0.9).targetOffset(0, 60), 24.0)
    screen.click()
    screen.write("0.9")
    screen.wait("stake_now_button.png")
    screen.click()
    screen.wait("check_button.png", 24.0)
    screen.click()
    screen.wait("stake_record.png", 24.0)
    println("profile $number: stake OK")
}