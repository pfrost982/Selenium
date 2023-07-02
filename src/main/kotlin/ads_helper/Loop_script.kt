package ads_helper

import ads_std.*
import org.sikuli.script.Pattern
import org.sikuli.script.Screen

suspend fun adsLanguageSwitchLoop() {
    val screen = Screen()
    screen.x = 2550
    screen.y = 0
    screen.w = 1300
    screen.h = 1100
    var profile = 263
    while (true) {
        println("Proxy label search...")
        val proxy = screen.exists(Pattern("ads_proxy_label.png").similar(0.9), 180.0)
        if (proxy != null) {
            println("Proxy label founded")
            screen.wait(2.0)
            scrollPattern(screen, Pattern("ads_proxy_label.png"), 3)
            screen.wait("ads_advanced_label.png")
            screen.queueTakeClickRelease()
            scrollPattern(screen, Pattern("ads_advanced_label.png"), 3)
            screen.wait(Pattern("ads_language_label.png").targetOffset(90, 0).similar(0.9))
            screen.queueClickRelease()
            screen.wait(Pattern("ads_switch_off.png").similar(0.9))
            println("Switch off")
            screen.wait(Pattern("ads_ok_button.png").similar(0.9))
            screen.queueTakeClickRelease()
            screen.wait(1.0)
            screen.wait(Pattern("ads_tips_label.png").targetOffset(600, 150).similar(0.9))
            screen.queueTakeClickRelease()
            screen.wait(2.0)
            profile++
            println("\u001B[32mNext profile $profile\u001B[39m")
        }
    }
}