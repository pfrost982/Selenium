package sui_discord_faucet

import ads_std.WALLET_PASS
import ads_std.closeProfile
import ads_std.insertTextTroughClipboard
import ads_std.openProfile
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Pattern
import org.sikuli.script.Screen

suspend fun stakeSuiScript(number: Int) {
    val driver = openProfile(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/sui_discord_faucet/png")
    try {
        screen.wait("extensions.png")
        screen.click()
        screen.wait("sui_icon.png")
        screen.click()
        screen.wait(Pattern("enter_password.png").targetOffset(0, 40))
        screen.click()
        insertTextTroughClipboard(screen, WALLET_PASS)
        screen.wait("unlock_button.png")
        screen.click()
        screen.wait("stake_button.png", 24.0)
        screen.click()
        screen.wait("nodes_guru_button.png", 24.0)
        screen.click()
        screen.wait("select_amount_button.png")
        screen.click()
        screen.wait(Pattern("enter_amount.png").similar(0.9).targetOffset(0, 60),24.0)
        screen.click()
        screen.write("0.9")
        screen.wait("stake_now_button.png")
        screen.click()
        screen.wait("check_button.png", 24.0)
        screen.click()
        screen.wait("stake_record.png", 24.0)
        println("profile $number: stake OK")
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }

    profileWork = false
    if (isError) {
        driver.quit()
    } else {
        closeProfile(number, driver)
    }
}