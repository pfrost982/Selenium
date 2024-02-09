package outlook

import ads_std.*
import org.sikuli.script.ImagePath
import org.sikuli.script.Pattern

suspend fun openOutlook(workRegion: WorkRegion, mail: String, password: String) {
    ImagePath.add("src/main/kotlin/outlook/png")
    val screen = workRegion.screen
    browserOpenUrl(screen, "https://outlook.live.com/mail/")
    screen.wait("outlook_sign_in_button_first.png", 32.0)
    screen.wait(2.0)
    screen.queueTakeClickRelease()
    browserWaitLoad(screen)
    browserCloseFirstTab(screen)
    screen.wait("outlook_mail_input.png", 12.0)
    screen.wait(1.0)
    screen.queueTakeClick()
    screen.paste(mail)
    screen.queueRelease()
    screen.wait("outlook_next_button.png")
    screen.queueTakeClickRelease()
    browserWaitLoad(screen)
    screen.wait("outlook_password_input.png")
    screen.wait(1.0)
    screen.queueTakeClick()
    screen.paste(password)
    screen.queueRelease()
    screen.wait("outlook_sign_in_button.png")
    screen.queueTakeClickRelease()
    browserWaitLoad(screen)
    screen.wait("outlook_skip_for_now.png")
    screen.wait(1.0)
    screen.queueTakeClickRelease()
    browserWaitLoad(screen)
    screen.wait("outlook_yes_button.png")
    screen.wait(1.0)
    screen.queueTakeClickRelease()
    browserWaitLoad(screen)
    tryToClickQueue(screen, Pattern("outlook_no_thanks.png"))
}