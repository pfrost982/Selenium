package sui_wave2

import ads_std.*
import org.sikuli.script.*
import org.sikuli.script.Mouse.WHEEL_DOWN
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.io.FileOutputStream

suspend fun getSuiScript(number: Int) {
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    //driver.get("https://discord.com/channels/916379725201563759/1037811694564560966")
    screen.wait(7.0)
    screen.wait("extensions.png")
    screen.click()
    screen.wait("sui_icon.png")
    screen.click()
    screen.wait(1.0)
    screen.wait(Pattern("enter_password.png").targetOffset(0, 40))
    screen.click()
    screen.paste(WALLET_PASS)
    screen.wait("unlock_button.png")
    screen.click()
    screen.wait(5.0)
    //driver.get("chrome-extension://kkedgchhdjjiopemikejclildleojceh/ui.html#/tokens?menu=%2Fnetwork")
    screen.wait("testnet_checkbox.png")
    screen.click()
    //driver.get("chrome-extension://kkedgchhdjjiopemikejclildleojceh/ui.html#/")

    val noSui = screen.exists(Pattern("no_sui.png").similar(0.9), 5.0)
    if (noSui == null) {
        val syncError = screen.exists(Pattern("sync_error.png").similar(0.9))
        if (syncError != null) {
            println("profile $number: wallet sync error")
        } else {
            println("profile $number: already have Sui, write to file")
            FileOutputStream(file, true).bufferedWriter().use { out ->
                out.append("$number")
                out.newLine()
                //closeProfile(number, driver)
            }
        }
    }
    println("profile $number: no Sui case")
    screen.wait("copy_to_clipboard.png")
    screen.click()
    val address = Toolkit.getDefaultToolkit().systemClipboard.getData(DataFlavor.stringFlavor).toString()
    println("profile $number: get address $address")
    //val walletTab = driver.windowHandle
    //driver.switchTo().newWindow(WindowType.TAB)
    //val discordTab = driver.windowHandle

    //driver.get("https://discord.com/channels/916379725201563759/1037811694564560966")
    //screen.wait(7.0)
    val input = screen.exists(Pattern("message_input.png").targetOffset(0, -150), 7.0)
    if (input == null) {
        screen.wait(Pattern("message_input_rus.png").targetOffset(0, -150))
    }
    screen.mouseMove()
    screen.wheel(WHEEL_DOWN, 24)
    val input2 = screen.exists("message_input.png", 7.0)
    if (input2 == null) {
        screen.wait("message_input_rus.png")
    }
    screen.click()
    screen.wait(2.0)
    screen.click()
    screen.write("faucet! ")
    screen.type("v", Key.CTRL)
    screen.type(Key.ENTER)
    screen.wait("general_chanel.png")
    screen.click()
    screen.wait(2.0)
    screen.wait("ads_icon.png")
    screen.click()
}