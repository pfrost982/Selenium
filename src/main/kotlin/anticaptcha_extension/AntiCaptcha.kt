package anticaptcha_extension

import ads_std.closeProfile
import ads_std.openProfile
import kotlinx.coroutines.delay
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Screen

suspend fun antiCaptcha(number: Int) {
    val driver = openProfile(number)
    println("profile $number: start galxe.com Username script on thread ${Thread.currentThread().name}")
    delay(3000)
    val screen = Screen()
    ImagePath.add("src/main/kotlin/anticaptcha_extension/png")
    try {
        screen.wait("extensions.png")
        screen.click()
        delay(1000)
        screen.wait("anticaptcha_face.png")
        screen.click()
        screen.wait("anticaptcha_key_input.png")
        screen.click()
        screen.write("3986a72449257661b95d99e206c96d37")
        screen.wait("anticaptcha_apply.png")
        screen.click()
        println("profile $number: key inputted")
    } catch (e: FindFailed) {
        e.printStackTrace()
    }
    delay(2000)
    closeProfile(number, driver)
    profileWork = false
}
