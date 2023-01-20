package primitive

import ads_std.closeAllTabs
import ads_std.closeProfile
import ads_std.insertTextTroughClipboard
import ads_std.openProfile
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Screen

suspend fun primitiveScript(number: Int) {
    val driver = openProfile(number)
    val mail = Mails.getMail(number)
    //val twitName = TwitterNames.getName(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    driver.manage().window().maximize()
    val screen = Screen()
    ImagePath.add("src/main/kotlin/primitive/png")
    try {
        screen.wait(2.0)
        closeAllTabs(driver)
        driver.get("https://primitive.xyz/")
        screen.wait(1.0)
        screen.wait("email_input.png")
        screen.click()
        insertTextTroughClipboard(screen, mail)
        screen.wait("mail_button")
        screen.click()
        /*
                screen.wait(2.0)
                driver.get("https://primitive.xyz/")
                screen.wait(1.0)
                screen.wait("email_input.png")
                screen.click()
                insertTextTroughClipboard(screen, twitName)
                screen.wait("twit_button")
                screen.click()
                screen.wait(2.0)
        */
        /*
                driver.get("https://discord.com/invite/primitive")
                screen.wait(1.0)
                screen.wait("accept_invite")
                screen.click()
        */
        screen.wait(3.0)

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