/*
package keplr_wallet_setup

import ads_std.SeedsEVM
import ads_std.WALLET_PASS
import ads_std.closeAllTabs
import ads_std.openProfile
import kotlinx.coroutines.delay
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Key
import org.sikuli.script.Mouse.WHEEL_DOWN
import org.sikuli.script.Screen
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection


suspend fun keplrSetupScript(number: Int) {
    val seed = SeedsEVM.getSeed(number)
    val driver = openProfile(number)
    println("profile $number: start keplr script on thread ${Thread.currentThread().name}")
    closeAllTabs(driver)
    driver.get("chrome-extension://lomdppafmjdhhfjkcedkaccckjpammag/popup.html#/register")
    delay(1000)
    val screen = Screen()
    ImagePath.add("src/main/kotlin/keplr_wallet_setup/png")
    try {
        screen.wait("import_existing_account_button.png")
        screen.click()
        val selection = StringSelection(seed)
        val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        clipboard.setContents(selection, selection)
        screen.wait("first_word_input.png")
        screen.click()
        screen.type("v", Key.CTRL)
        screen.wait("account_name_input.png")
        screen.click()
        screen.write("A%03d".format(number))
        screen.wait("new_password_input.png")
        screen.click()
        screen.write(WALLET_PASS)
        screen.wait("confirm_password_input.png")
        screen.click()
        screen.write(WALLET_PASS)
        screen.wheel(WHEEL_DOWN, 5)
        screen.wait("next_button.png")
        screen.click()
        delay(3500)
        screen.wait("done_button.png")
        screen.click()
        println("profile $number: seed inputted")
    } catch (e: FindFailed) {
        e.printStackTrace()
    }
    driver.quit()
    profileWork = false
}
*/
