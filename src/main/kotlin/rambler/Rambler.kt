package rambler

import ads_std.*
import org.sikuli.script.*

suspend fun enterAndChangePasswordRambler(workRegion: WorkRegion) {
    val screen = workRegion.screen
    val mail = Mails.getMail(workRegion.profile)
    println(mail)
    val password = RamblerPaswords.getPassword(workRegion.profile)
    println(password)
    val newPassword = password + "Ads"
    openUrlSikuliDark(screen, "https://mail.rambler.ru/folder/INBOX/")
    screen.wait(3.0)
    screen.wait("browser_refresh_button_dark.png", 24.0)
    val inputMail = screen.exists("rambler_mail_input.png", 8.0)
    if (inputMail != null) {
        screen.queueTakeClick()
        screen.paste(mail)
        screen.type(Key.TAB)
        screen.paste(password)
        screen.type(Key.ENTER)
        screen.queueRelease()
    }
    screen.wait("rambler_inbox.png", 24.0)
    screen.wait(0.5)
    screen.wait("rambler_inbox.png")
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    val noFavorite = screen.exists(Pattern("browser_star_no_favorite.png").similar(0.95))
    if (noFavorite != null) {
        screen.queueTakeClick()
        screen.wait("browser_ready_button.png")
        screen.queueClickRelease()
        screen.wait(1.0)
    }
    openUrlSikuliDark(screen, "https://id.rambler.ru/account/change-password")
    screen.wait(4.0)
    screen.wait(Pattern("captcha_i_am_human.png").similar(0.95), 120.0)
    screen.wait("rambler_current_password.png")
    screen.queueTakeClick()
    screen.paste(password)
    screen.queueRelease()
    screen.wait(Pattern("captcha_i_am_human.png").similar(0.95).targetOffset(0, -84))
    screen.queueTakeClick()
    screen.paste(newPassword)
    println(newPassword)
    screen.type(Key.ENTER)
    screen.queueRelease()
    screen.wait(Pattern("rambler_password_changed.png").similar(0.95), 8.0)
    screen.wait(1.0)
    openUrlSikuliDark(screen, "chrome://settings/passwords")
    screen.wait("browser_add_button.png")
    screen.wait(1.0)
    screen.wait("browser_add_button.png")
    screen.queueTakeClick()
    screen.wait(0.5)
    screen.paste("id.rambler.ru")
    screen.type(Key.TAB)
    screen.paste(mail)
    screen.type(Key.TAB)
    screen.paste(newPassword)
    screen.type(Key.ENTER)
    screen.queueRelease()
    screen.wait(1.0)
    screen.wait(Pattern("browser_id_rambler_ru.png").targetOffset(465, 0))
    screen.queueTakeClickRelease()
    screen.wait(Pattern("browser_id_rambler_ru.png").targetOffset(360, 0))
    screen.queueTakeClick()
    screen.type("c", Key.CTRL)
    val text = getTextFromClipboard()
    screen.queueRelease()
    if (text == newPassword) {
        println("See new password!!!")
        screen.wait(2.0)
    } else {
        throw IllegalArgumentException("Not see new password!!!")
    }
}
