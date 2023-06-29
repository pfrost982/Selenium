/*
package fuel_wallet

import ads_std.closeAllTabs
import ads_std.openProfile
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Screen

suspend fun fuelDiscordScript(number: Int) {
    val driver = openProfile(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    closeAllTabs(driver)
    //driver.manage().window().maximize()
    driver.get("https://discord.com/invite/xfpK4Pe")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/fuel_wallet/png")
    try {
        screen.wait(3.0)
        screen.wait("browser_accept_invite_button.png")
        screen.click()
        screen.wait(15.0)
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }
    profileWork = false
    if (isError) {
        driver.quit()
    } else {
        driver.quit()
        //closeProfile(number, driver)
    }
}*/
