package rambler

import ads_std.closeAllTabs
import ads_std.closeProfile
import ads_std.openProfile
import ads_std.putTextInClipboard
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
    closeAllTabs(driver)
    driver.manage().window().maximize()
    driver.get("https://mail.rambler.ru/folder/INBOX/")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/rambler/png")
    try {
        val isNeedLogin = screen.exists("mail_input.png", 10.0)
        if (isNeedLogin != null) {
            println("profile $number: need login")
            screen.click(isNeedLogin)
            putTextInClipboard(mail)
            screen.type("a", Key.CTRL)
            screen.type("v", Key.CTRL)
            screen.type(Key.TAB)
            putTextInClipboard(password)
            screen.type("a", Key.CTRL)
            screen.type("v", Key.CTRL)
            screen.wait("login_button.png", 10.0)
            screen.click()
        }
        screen.wait(7.0)
        driver.get("https://mail.rambler.ru/settings/security")
        screen.wait("change_password.png", 10.0)
        screen.click()
        delay(1000)

        val changePassword = screen.exists("change_password.png")
        if (changePassword != null) {
            screen.click(changePassword)
        }

        val isCaptcha = screen.exists("captcha_icon.png", 10.0)
        if (isCaptcha != null) {
            println("profile $number: anticaptcha detected")
            screen.wait("solved.png", 180.0)
        }

        val currentPassword = screen.wait("current_password_input.png", 10.0)
        screen.click(currentPassword)
        putTextInClipboard(password)
        screen.type("a", Key.CTRL)
        screen.type("v", Key.CTRL)

        val newPasswordInput = screen.wait("new_password_input.png")
        screen.click(newPasswordInput)
        putTextInClipboard(newPassword)
        screen.type("a", Key.CTRL)
        screen.type("v", Key.CTRL)
        val saveButton = screen.wait("new_password_save_button.png", 10.0)
        screen.click(saveButton)
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
        isThereAreMistakes = true
    }
    profileWork = false
    if (isThereAreMistakes) {
        driver.quit()
    } else {
        closeProfile(number, driver)
    }
}
