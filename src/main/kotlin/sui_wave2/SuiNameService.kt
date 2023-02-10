package sui_wave2

import ads_std.*
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Pattern
import org.sikuli.script.Screen
import java.io.FileOutputStream

suspend fun getNameService(number: Int) {
    val driver = openProfile(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    val username = TwitLogins.getLogin(number)
    ImagePath.add("src/main/kotlin/sui_wave2/png")
    var allRight = true
    try {
        closeAllTabs(driver)
        driver.get("https://suins.io/")
        val loginButton = screen.exists(Pattern("suins_connect_wallet_button.png").similar(0.9), 5.0)
        if (loginButton != null) {
            println("profile $number: Wallet not connected")
            screen.click()
            screen.wait("suins_read_understand.png")
            screen.click()
            screen.wait("suins_sui_wallet.png")
            screen.click()
            screen.wait(Pattern("enter_password.png").targetOffset(0, 40))
            screen.click()
            insertTextTroughClipboard(screen, WALLET_PASS)
            screen.wait("unlock_button.png")
            screen.click()
            screen.wait("wallet_connect_button.png")
            screen.click()
        } else {
            println("profile $number: Wallet already connected")
        }
        screen.wait("suins_search_names.png")
        screen.click()
        screen.write(username)
        screen.wait("suins_search_icon.png")
        screen.click()
        val selectButton = screen.exists(Pattern("suins_select_button.png").similar(0.9), 120.0)
        if (selectButton == null) {
            println("profile $number: No select button")
            FileOutputStream(file, true).bufferedWriter().use { out ->
                out.append("$number no select button")
                out.newLine()
                allRight = false
            }
        }
        if (allRight) {
            println("profile $number: Press select button")
            screen.click()
            val registerButton = screen.exists(Pattern("suins_register_name_button.png").similar(0.9), 120.0)
            if (registerButton == null) {
                println("profile $number: No register button")
                FileOutputStream(file, true).bufferedWriter().use { out ->
                    out.append("$number no register button")
                    out.newLine()
                    allRight = false
                }
            }
        }
        if (allRight) {
            println("profile $number: Press register button")
            screen.click()
            val enterPassword = screen.exists(Pattern("enter_password.png").targetOffset(0, 40))
            if (enterPassword != null) {
                println("profile $number: Enter password")
                screen.click()
                insertTextTroughClipboard(screen, WALLET_PASS)
                screen.wait("unlock_button.png")
                screen.click()
                screen.wait("wallet_connect_button.png")
                screen.click()
            }
        }
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