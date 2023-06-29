/*
package anticaptcha_extension

import ads_std.ANTICAPTCHA_KEY
import ads_std.closeProfile
import ads_std.openProfile
import kotlinx.coroutines.delay
import org.sikuli.script.*

suspend fun antiCaptcha(number: Int) {
    val driver = openProfile(number)
    println("profile $number: start anticaptcha script on thread ${Thread.currentThread().name}")
    delay(3000)
    val screen = Screen()
    ImagePath.add("src/main/kotlin/anticaptcha_extension/png")
    try {
        screen.wait("extensions.png")
        screen.click()
        delay(1000)
        screen.wait("anticaptcha_face.png")
        screen.click()
        screen.wait("anticaptcha_key_input_offset.png")
        screen.click()
        screen.type("a", Key.CTRL)
        screen.write(ANTICAPTCHA_KEY)
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
*/
