package twitter

import ads_std.WorkRegion
import ads_std.browserOpenUrl
import ads_std.queueClickRelease
import ads_std.queueTakeClick
import org.sikuli.script.Key
import org.sikuli.script.Pattern

suspend fun getTwitToken(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    browserOpenUrl(screen, "https://twitter.com/")
    screen.type(Key.F12)
    screen.wait(7.0)
    screen.wait("browser_f12_more_tabs.png")
    screen.queueTakeClick()
    screen.wait("browser_f12_application.png")
    screen.queueClickRelease()
    screen.wait(1.0)
    screen.wait(Pattern("browser_f12_more_tabs.png").targetOffset(-288, 238))
    screen.queueClickRelease()
    screen.wait(1.0)
    screen.wait(Pattern("browser_f12_more_tabs.png").targetOffset(-208, 262))
    screen.queueClickRelease()
    screen.wait(1.0)
    screen.wait(Pattern("browser_f12_auth_.png"))
    screen.queueClickRelease()
}

suspend fun openTwitter(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    browserOpenUrl(screen, "https://twitter.com/home")
}
