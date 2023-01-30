package sui_discord_faucet

import ads_std.*
import org.openqa.selenium.WindowType
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Pattern
import org.sikuli.script.Screen
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard

suspend fun suiScript(number: Int) {
    val driver = openProfile(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    driver.manage().window().maximize()
    val screen = Screen()
    ImagePath.add("src/main/kotlin/sui_discord_faucet/png")
    try {
        screen.wait(2.0)
        closeAllTabs(driver)
        driver.get("chrome-extension://kkedgchhdjjiopemikejclildleojceh/ui.html#/")
        screen.wait(Pattern("enter_password.png").targetOffset(0, 40))
        screen.click()
        insertTextTroughClipboard(screen, WALLET_PASS)
        screen.wait("unlock_button.png")
        screen.click()
        screen.wait("copy_to_clipboard.png")
        screen.click()
        val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val address = clipboard.getContents("script").toString()
        println(address)
/*
        val walletTab = driver.windowHandle
        driver.switchTo().newWindow(WindowType.TAB)
        val discordTab = driver.windowHandle
        driver.get("https://discord.com/channels/916379725201563759/1037811694564560966")
*/

        screen.wait(2.0)
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }

    isError = true

    profileWork = false
    if (isError) {
        driver.quit()
    } else {
        closeProfile(number, driver)
    }
}