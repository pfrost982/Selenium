package suge

import ads_std.closeAllTabs
import ads_std.closeProfile
import ads_std.openProfile
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
        screen.wait(3.0)
        closeAllTabs(driver)
        driver.get("https://twitter.com/Surge_Fi")
        screen.wait(3.0)
        val follow = screen.exists("follow.png", 5.0)
        if (follow != null) {
            println("profile $number: not follow case")
            screen.click(follow)
        } else{
            println("profile $number: already follow case")
        }
        val black = screen.exists("following_black.png")
        if (black != null) {
            println("profile $number: black follow case")
        } else {
            val white = screen.exists("following_white.png")
            if (white != null){
                println("profile $number: white follow case")
            } else {
                println("profile $number: no black and white case")
                isError = true
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
}