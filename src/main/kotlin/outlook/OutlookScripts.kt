package outlook

import ads_std.*
import org.sikuli.script.ImagePath
import org.sikuli.script.Pattern
import sui_wave2.file

suspend fun openOutlook(workRegion: WorkRegion, mail: String, password: String) {
    ImagePath.add("src/main/kotlin/outlook/png")
    val screen = workRegion.screen
    openUrlSikuliDark(screen, "https://login.live.com")
    screen.wait("outlook_mail_input.png", 24.0)
    screen.wait(1.0)
    screen.queueTakeClick()
    screen.paste(mail)
    screen.queueRelease()
    screen.wait("outlook_next_button.png")
    screen.queueTakeClickRelease()
    screen.wait("outlook_password_input.png", 12.0)
    screen.wait(1.0)
    screen.queueTakeClick()
    screen.paste(mail)
    screen.queueRelease()
    screen.wait("outlook_sign_in_button.png")
    screen.queueTakeClickRelease()
    browserWaitLoad(screen, 24.0)
    if (tryToClickQueue(screen, Pattern("outlook_continue_button.png"))) {
        browserWaitLoad(screen, 24.0)
    }
    if (tryToClickQueue(screen, Pattern("outlook_yes_button.png"))) {
        browserWaitLoad(screen, 24.0)
    }

}