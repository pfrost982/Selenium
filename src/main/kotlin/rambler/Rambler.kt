package rambler

import ads_std.closeTabs
import ads_std.openProfile
import kotlinx.coroutines.delay
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Key
import org.sikuli.script.Screen

suspend fun ramblerScript(number: Int) {
    val mail = RamblerMails.getMail(number)
    val password = RamblerPaswords.getPassword(number)
    val newPassword = password + "Ads"
    val driver = openProfile(number)

    println("profile $number: start script on thread ${Thread.currentThread().name}")
    closeTabs(driver)
    driver.manage().window().maximize()
    driver.get("https://mail.rambler.ru/folder/INBOX/")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/rambler/png")
    try {
        screen.paste(screen.wait("mail_input.png", 10.0), mail)
        screen.type(Key.TAB)
        screen.paste(password)
        screen.wait("login_button.png", 10.0)
        screen.click()
        delay(10000)
        driver.get("https://mail.rambler.ru/settings/security")
        screen.wait("change_password.png", 10.0)
        screen.click()
        screen.wait("solved.png", 180.0)
        screen.paste(screen.wait("current_password_input.png", 10.0), password)
        screen.type(Key.TAB)
        screen.paste(newPassword)
        screen.click(screen.wait("new_password_save_button.png", 10.0))
        screen.wait("change_password.png", 10.0)
        driver.get("https://mail.rambler.ru/folder/INBOX/")
        delay(1000)
        screen.wait("star.png")
        screen.click()
        screen.wait("bookmark_ready_button.png")
        screen.click()
        driver.get("chrome://settings/passwords")
        screen.wait("add_password_button.png")
        screen.click()
        screen.wait("site_input.png")
        screen.paste("https://mail.rambler.ru/folder/INBOX/")
        screen.type(Key.TAB)
        screen.paste(mail)
        screen.type(Key.TAB)
        screen.paste(newPassword)
        screen.wait("save_password_button.png")
        screen.click()
        println("profile $number: script ended")
    } catch (e: FindFailed) {
        e.printStackTrace()
    }
    driver.quit()
    profileWork = false
}
