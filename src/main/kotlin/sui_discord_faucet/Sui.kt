package sui_discord_faucet

import ads_std.*
import org.openqa.selenium.WindowType
import org.sikuli.script.*
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.io.FileOutputStream

suspend fun getSuiScript(number: Int) {
    val driver = openProfile(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    driver.manage().window().maximize()
    val screen = Screen()
    ImagePath.add("src/main/kotlin/sui_discord_faucet/png")
    try {
        closeAllTabs(driver)
        driver.get("chrome-extension://kkedgchhdjjiopemikejclildleojceh/ui.html#/")
        screen.wait(Pattern("enter_password.png").targetOffset(0, 40))
        screen.click()
        insertTextTroughClipboard(screen, WALLET_PASS)
        screen.wait("unlock_button.png")
        screen.click()
        screen.wait(3.0)
/*
        driver.get("chrome-extension://kkedgchhdjjiopemikejclildleojceh/ui.html#/tokens?menu=%2Fnetwork")
        screen.wait("testnet_checkbox.png")
        screen.click()
        driver.get("chrome-extension://kkedgchhdjjiopemikejclildleojceh/ui.html#/")
*/
        val noSui = screen.exists(Pattern("no_sui.png").similar(0.95), 5.0)
        if (noSui != null) {
            println("profile $number: no Sui case")
            screen.wait("copy_to_clipboard.png")
            screen.click()
            val address = Toolkit.getDefaultToolkit().systemClipboard.getData(DataFlavor.stringFlavor).toString()
            println("profile $number: get address $address")
            val walletTab = driver.windowHandle
            driver.switchTo().newWindow(WindowType.TAB)
            val discordTab = driver.windowHandle
            driver.get("https://discord.com/channels/916379725201563759/1037811694564560966")
            screen.wait(7.0)
            val input = screen.exists("message_input.png", 7.0)
            if (input == null) {
                screen.wait("message_input_rus.png")
            }
            screen.click()
            screen.wait(2.0)
            screen.click()
            screen.write("faucet! ")
            screen.type("v", Key.CTRL)
            screen.type(Key.ENTER)
            screen.wait(2.0)
            screen.type(Key.ENTER)
            /*
                        screen.wait(4.0)
                        screen.wait("general_chanel.png")
                        screen.click()
                        screen.wait(2.0)
            */
            /*
                        screen.wait("ads_icon.png")
                        screen.click()
            */
        } else {
            val syncError = screen.exists(Pattern("no_sui.png").similar(0.95))
            if (syncError != null) {
                println("profile $number: wallet sync error")
            } else {
                println("profile $number: already have Sui")
                FileOutputStream(file, true).bufferedWriter().use { out ->
                    out.append("$number")
                    out.newLine()
                }
            }
        }
        screen.wait("ads_icon.png")
        screen.click()
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }

    profileWork = false
    if (isError) {
        driver.quit()
    } else {
        driver.quit()
        //closeProfile(number, driver)
    }
}