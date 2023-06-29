/*
package dune

import ads_std.DUNE_PASS
import ads_std.openProfile
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Pattern
import org.sikuli.script.Screen

suspend fun registrationScript(number: Int) {
    val driver = openProfile(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val username = logins.getLogin(number).lowercase()
    val mail = mails.getMail(number)
    val screen = Screen()
    ImagePath.add("src/main/kotlin/dune/png")
    try {
        driver.get("https://dune.com/auth/register")
        screen.wait(Pattern("pick_username.png").targetOffset(0,45), 24.0)
        screen.click()
        screen.paste(username)
        screen.wait(Pattern("email_input.png").targetOffset(0,45))
        screen.click()
        screen.paste(mail)
        screen.wait(Pattern("password_input.png").targetOffset(0,45))
        screen.click()
        screen.paste(DUNE_PASS)
        screen.wait("create_account_button.png")
        screen.click()
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
