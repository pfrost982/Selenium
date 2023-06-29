/*
package sui_wave2

import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Mouse.WHEEL_DOWN
import org.sikuli.script.Pattern
import org.sikuli.script.Screen
import java.io.FileOutputStream

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
        screen.wait("wallet_connect_button.png")
        screen.click()
        driver.get("https://testnet.capy.art/collection")
        var getFree = screen.exists(Pattern("capy_get_free_button.png").similar(0.9), 30.0)
        while(getFree == null){
            println("profile $number: Refresh")
            driver.navigate().refresh()
            getFree = screen.exists(Pattern("capy_get_free_button.png").similar(0.9), 30.0)
        }
        screen.click()

        screen.wait(Pattern("enter_password.png").targetOffset(0, 40))
        screen.click()
        screen.paste(WALLET_PASS)
        screen.wait("unlock_button.png")
        screen.click()

        val walletMintPage = screen.exists(Pattern("capy_testnet_art.png").similar(0.9), 180.0)
        if (walletMintPage != null) {
            screen.wait(7.0)
            screen.wheel(WHEEL_DOWN, 4)
            println("profile $number: Wheel down 1st")
            screen.wait(1.0)
            screen.wait("wallet_approve_button.png")
            screen.click()
            val getCapy = screen.exists(Pattern("capy_properties.png").similar(0.9), 30.0)
            if (getCapy != null) {
                println("profile $number: Capy minted")
            } else {
                FileOutputStream(file, true).bufferedWriter().use { out ->
                    out.append("$number")
                    out.newLine()
                }
                println("profile $number: Don't see capy")
            }
        } else {
            FileOutputStream(file, true).bufferedWriter().use { out ->
                out.append("$number")
                out.newLine()
            }
            println("profile $number: Wallet mint page not loaded")
        }
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
}*/
