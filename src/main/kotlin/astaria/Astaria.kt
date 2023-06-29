/*
package astaria

import ads_std.*
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Screen

suspend fun AstariaScript(number: Int) {
    val mail = Mails.getMail(number)
    val driver = openProfile(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    driver.manage().window().maximize()
    val screen = Screen()
    ImagePath.add("src/main/kotlin/astaria/png")
    try {
        screen.wait(2.0)
        closeAllTabs(driver)
        driver.get("https://astaria.xyz/")
        screen.wait(1.0)
        screen.wait("email_input.png")
        screen.click()
        screen.paste(mail)
        screen.wait("subscribe_button.png")
        screen.click()
        screen.wait("subscribed.png", 5.0)
        screen.wait(1.0)
        var isDone = false
        while (!isDone) {
            driver.get("https://twitter.com/AstariaXYZ")
            val follow = screen.exists("follow.png", 5.0)
            if (follow != null) {
                println("profile $number: follow case")
                screen.wait(2.0)
                screen.click(follow)
            } else {
                println("profile $number: no follow case")
                isDone = true
            }
            val black = screen.exists("following_black.png", 5.0)
            if (black != null) {
                println("profile $number: black following case")
                isDone = true
            } else {
                val white = screen.exists("following_white.png")
                if (white != null) {
                    println("profile $number: white following case")
                    isDone = true
                } else {
                    println("profile $number: no black and white following case, repeat")
                }
            }
        }
        screen.wait(2.0)
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
}*/
