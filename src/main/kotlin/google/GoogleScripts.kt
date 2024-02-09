package google

import ads_std.WorkRegion
import ads_std.browserOpenUrl

suspend fun openGmail(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    browserOpenUrl(screen, "gmail.com")
    screen.wait("gmail_inbox_button.png", 18.0)
    screen.wait(2.0)
}
