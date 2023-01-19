package kresko

import ads_std.closeAllTabs
import ads_std.closeProfile
import ads_std.insertTextTroughClipboard
import ads_std.openProfile
import kotlinx.coroutines.delay
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Pattern
import org.sikuli.script.Screen

suspend fun kreskoScript(number: Int) {
    val driver = openProfile(number)
    val mail = Mails.getMail(number)
    val name = TwitterNames.getName(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    driver.manage().window().maximize()
    val screen = Screen()
    ImagePath.add("src/main/kotlin/kresko/png")
    try {
        delay(2000)
        closeAllTabs(driver)
        driver.get("https://www.kresko.fi/")
        screen.wait(1.0)
        screen.wait("not_robot.png")
        screen.click()
        screen.wait("email_input.png")
        screen.click()
        insertTextTroughClipboard(screen, mail)
        screen.wait("name_input.png")
        screen.click()
        insertTextTroughClipboard(screen, name)
        screen.wait(Pattern("right_arrow.png").similar(0.95), 180.0)
        screen.click()
        screen.wait(1.0)
        driver.get("https://mail.rambler.ru/folder/INBOX")
        val pochta = screen.exists("pochta.png")
        if (pochta != null) {
            screen.click()
            screen.wait("planet.png")
            screen.click()
            screen.wait("voyti.png")
            screen.click()
        }
        screen.wait(2.0)
        checkNotifications(screen)
        screen.wait("spam.png")
        screen.click()
        screen.wait("kresko_mail_title.png")
        screen.click()
        screen.wait("vklyuchit_ssilki.png")
        screen.click()
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }

    isError = true

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