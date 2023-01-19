package rambler

import ads_std.closeAllTabs
import ads_std.closeProfile
import ads_std.insertTextTroughClipboard
import ads_std.openProfile
import org.sikuli.script.*

suspend fun ramblerScript(number: Int) {
    val mail = RamblerMails.getMail(number)
    val password = RamblerPaswords.getPassword(number)
    val newPassword = password + "Ads"
    val driver = openProfile(number)

    println("profile $number: start script on thread ${Thread.currentThread().name}")
    //closeAllTabs(driver)
    driver.manage().window().maximize()
    //driver.get("https://mail.rambler.ru/folder/INBOX/")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/rambler/png")
    try {
/*
        val isNeedLogin = screen.exists("mail_input.png", 10.0)
        if (isNeedLogin != null) {
            println("profile $number: need login")
            screen.click(isNeedLogin)
            insertTextTroughClipboard(screen, mail)
            screen.type(Key.TAB)
            insertTextTroughClipboard(screen, password)
            screen.wait("login_button.png", 10.0)
            screen.click()
        }

        var settings = screen.exists(Pattern("settings.png").similar(0.9), 10.0)
        while (settings == null) {
            driver.get("https://mail.rambler.ru/folder/INBOX")
            checkNotifications(screen)
            settings = screen.exists("settings.png", 10.0)
        }
        checkNotifications(screen)
        screen.click(settings)

        var security = screen.exists("security.png", 10.0)
        while (security == null) {
            driver.get("https://mail.rambler.ru/settings/main")
            security = screen.exists("security.png", 10.0)
        }
        checkNotifications(screen)
        screen.click(security)

        var changePassword = screen.exists("change_password.png", 10.0)
        while (changePassword == null) {
            driver.get("https://mail.rambler.ru/settings/security")
            changePassword = screen.exists("security.png", 10.0)
        }
        screen.click(changePassword)

        var isCaptcha = screen.exists("captcha_icon.png", 15.0)
        while (isCaptcha == null) {
            screen.wait("update.png")
            screen.click()
            isCaptcha = screen.exists("captcha_icon.png", 10.0)
        }

        screen.wait(Pattern("solved.png").similar(0.9), 180.0)

        val currentPassword = screen.wait("current_password_input.png", 10.0)
        screen.click(currentPassword)
        insertTextTroughClipboard(screen, password)

        val newPasswordInput = screen.wait("new_password_input.png")
        screen.click(newPasswordInput)
        insertTextTroughClipboard(screen, newPassword)
        val saveButton = screen.wait("new_password_save_button.png", 10.0)
        screen.click(saveButton)
        screen.wait("change_password.png", 10.0)
        driver.get("https://mail.rambler.ru/folder/INBOX/")
        screen.wait(2.0)
        screen.wait("star.png")
        screen.click()
        screen.wait("bookmark_ready_button.png")
        screen.click()
*/
        screen.wait(2.0)
        closeAllTabs(driver)
        driver.get("chrome://settings/passwords")
        screen.wait("add_password_button.png")
        screen.click()
        screen.wait("site_input.png")
        insertTextTroughClipboard(screen, "https://mail.rambler.ru/folder/INBOX/")
        screen.type(Key.TAB)
        insertTextTroughClipboard(screen, mail)
        screen.type(Key.TAB)
        insertTextTroughClipboard(screen, newPassword)
        screen.wait("save_password_button.png")
        screen.click()
        println("profile $number: script ended")
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

fun checkNotifications(screen: Screen) {
    val blockNotifications = screen.exists("block_notifications.png")
    if (blockNotifications != null) {
        screen.click(blockNotifications)
    }
}