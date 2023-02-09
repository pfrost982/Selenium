package sui_discord_faucet

import ads_std.*
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Mouse.WHEEL_DOWN
import org.sikuli.script.Pattern
import org.sikuli.script.Screen

suspend fun getCapyScript(number: Int) {
    val driver = openProfile(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/sui_discord_faucet/png")
    try {
        closeAllTabs(driver)
        driver.get("https://testnet.capy.art/collection")
        screen.wait("capy_menu_button.png", 7.0)
        screen.click()
        screen.wait("capy_connect_wallet_button.png")
        screen.click()
        screen.wait("capy_sui_wallet_button.png")
        screen.click()
        screen.wait(Pattern("enter_password.png").targetOffset(0, 40))
        screen.click()
        insertTextTroughClipboard(screen, WALLET_PASS)
        screen.wait("unlock_button.png")
        screen.click()
        screen.wait("capy_connect_button.png")
        screen.click()
        driver.get("https://testnet.capy.art/collection")
        screen.wait("capy_get_free_button.png", 30.0)
        screen.click()
        screen.wait("capy_testnet_art.png", 30.0)
        screen.wait(3.0)
        screen.wheel(WHEEL_DOWN, 3)
        println("Wheel down")
        screen.wait(1.0)
        screen.wait("capy_approve_button.png")
        screen.click()
        screen.wait("capy_properties.png", 30.0)
        screen.wait(3.0)
        println("profile $number: Capy minted!")
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }

    profileWork = false
    if (isError) {
        driver.quit()
    } else {
        //driver.quit()
        closeProfile(number, driver)
    }
}