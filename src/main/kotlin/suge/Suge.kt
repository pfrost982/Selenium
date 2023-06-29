/*
package suge

import ads_std.closeAllTabs
import ads_std.closeProfile
import ads_std.openProfile
import kotlinx.coroutines.delay
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Screen

suspend fun SugeScript(number: Int) {
    val driver = openProfile(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    driver.manage().window().maximize()
    val screen = Screen()
    ImagePath.add("src/main/kotlin/suge/png")
    try {
        delay(2000)
        closeAllTabs(driver)
        var isDone = false
        while (!isDone) {
            driver.get("https://twitter.com/Surge_Fi")
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
                println("profile $number: black follow case")
                isDone = true
            } else {
                val white = screen.exists("following_white.png")
                if (white != null) {
                    println("profile $number: white follow case")
                    isDone = true
                } else {
                    println("profile $number: no black and white following case, repeat")
                }
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
        closeProfile(number, driver)
    }
}*/
