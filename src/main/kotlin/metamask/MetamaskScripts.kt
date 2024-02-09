package metamask

import ads_std.*
import org.sikuli.script.Pattern

suspend fun changeLanguageToEnglish(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    browserOpenUrl(screen, "chrome-extension://mldahpfajfokgbplbigojfbmmagidkge/home.html#settings")
    val ru = screen.exists("metamask_unlock_ru.png")
    if (ru != null) {
        val language = metamaskUnlock(screen)
        println("profile ${workRegion.profile}: language = $language")
        browserOpenUrl(screen, "chrome-extension://mldahpfajfokgbplbigojfbmmagidkge/home.html#settings")
        screen.wait(Pattern("metamask_current_language_ru.png").targetOffset(0, 70))
        screen.queueTakeClick()
        screen.type("e")
        screen.queueClickRelease()
        screen.wait(1.0)
        screen.wait("metamask_current_language.png")
        println("profile ${workRegion.profile}: set language to english")
    } else {
        println("profile ${workRegion.profile}: language = en")
    }
    screen.wait(3.0)
}