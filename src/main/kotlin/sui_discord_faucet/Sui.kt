package sui_discord_faucet

import ads_std.*
import org.openqa.selenium.WindowType
import org.sikuli.script.*
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor

suspend fun getSuiScript(number: Int) {
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
        screen.wait(3.0)
        driver.get("chrome-extension://kkedgchhdjjiopemikejclildleojceh/ui.html#/tokens?menu=%2Fnetwork")
        screen.wait("testnet_checkbox.png")
        screen.click()
        driver.get("chrome-extension://kkedgchhdjjiopemikejclildleojceh/ui.html#/")
        val noSui = screen.exists(Pattern("no_sui.png").similar(0.95), 5.0)
        if (noSui != null) {
            println("profile $number: no Sui case")
            screen.wait("copy_to_clipboard.png")
            screen.click()
            val address = Toolkit.getDefaultToolkit().systemClipboard.getData(DataFlavor.stringFlavor).toString()
            println(address)
            val walletTab = driver.windowHandle
            driver.switchTo().newWindow(WindowType.TAB)
            val discordTab = driver.windowHandle
            driver.get("https://discord.com/channels/916379725201563759/1037811694564560966")
            screen.wait(7.0)
            screen.wait("message_input.png")
            screen.click()
            insertTextTroughClipboard(screen, "faucet! $address")
            screen.type(Key.ENTER)
            screen.wait(1.0)
            screen.type(Key.ENTER)
            screen.wait(4.0)
        } else {
            println("profile $number: already have Sui")
        }
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