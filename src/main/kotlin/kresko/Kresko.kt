package kresko

import ads_std.*
import kotlinx.coroutines.delay
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Pattern
import org.sikuli.script.Screen

suspend fun kreskoScript(number: Int) {
    val driver = openProfile(number)
    val mail = Mails.getMail(number)
    val name = TwitLogins.getLogin(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    driver.manage().window().maximize()
    val screen = Screen()
    ImagePath.add("src/main/kotlin/kresko/png")
    try {
        delay(2000)
        closeAllTabs(driver)
        driver.get("https://www.kresko.fi/")
        screen.wait(1.0)
        screen.wait("email_input.png")
        screen.click()
        insertTextTroughClipboard(screen, mail)
        screen.wait("name_input.png")
        screen.click()
        insertTextTroughClipboard(screen, name)
        screen.wait("not_robot.png")
        screen.click()
        screen.mouseMove(50, 0)
        var arrow = screen.exists(Pattern("right_arrow.png").similar(0.95), 180.0)
        while (arrow == null) {
            driver.get("https://www.kresko.fi/")
            screen.wait(1.0)
            screen.wait("email_input.png")
            screen.click()
            insertTextTroughClipboard(screen, mail)
            screen.wait("name_input.png")
            screen.click()
            insertTextTroughClipboard(screen, name)
            screen.wait("not_robot.png")
            screen.click()
            screen.mouseMove(50, 0)
            arrow = screen.exists(Pattern("right_arrow.png").similar(0.95), 180.0)
        }
        screen.click()
        screen.wait(1.0)
        driver.get("https://mail.rambler.ru/folder/INBOX")
        val pochta = screen.exists(Pattern("pochta.png").similar(0.9), 9.0)
        println(pochta)
        if (pochta != null) {
            println("profile $number: mail input case ${Thread.currentThread().name}")
            screen.click()
            screen.wait("planet.png")
            screen.click()
            screen.wait("voyti.png")
            screen.click()
        } else {
            println("profile $number: in mail case ${Thread.currentThread().name}")
        }
        screen.wait(2.0)
        screen.wait(Pattern("spam.png").similar(0.9), 10.0)
        screen.wait(0.7)
        checkNotifications(screen)
        screen.wait(Pattern("spam.png").similar(0.9))
        screen.click()
        screen.wait("kresko_mail_title.png", 5.0)
        screen.click()
        screen.wait("vklyuchit_ssilki.png")
        screen.click()
        screen.wait(Pattern("confirm_waitlist.png").similar(0.95))
        screen.wait(0.7)
        screen.wait(Pattern("confirm_waitlist.png").similar(0.95))
        screen.click()
        screen.wait(5.0)
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