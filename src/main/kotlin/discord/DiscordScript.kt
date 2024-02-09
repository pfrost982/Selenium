package discord

import ads_std.*
import org.sikuli.script.ImagePath
import org.sikuli.script.Pattern
import org.sikuli.script.Screen
import rambler.openRambler
import java.io.File

val discord_names_file = File("src/main/kotlin/discord/discord_names.txt")
suspend fun cometaOpenDiscord(workRegion: WorkRegion, token: String) {
    val screen = workRegion.screen
    browserOpenExtension(screen, Pattern("cometa_extension.png"))
    screen.wait(1.0)
    screen.wait(Pattern("cometa_login_discord.png").targetOffset(0, 55))
    screen.queueTakeClick()
    screen.paste(token)
    screen.queueRelease()
    screen.wait(1.0)
    screen.wait(Pattern("cometa_login_discord.png").targetOffset(0, 105))
    screen.queueTakeClickRelease()
    screen.wait("discord_settings_icon.png", 32.0)
}

suspend fun newDiscord(workRegion: WorkRegion) {
    val offset = 150
    ImagePath.add("src/main/kotlin/discord/png")
    val screen = workRegion.screen
    screen.wait(2.0)
    val mail = DiscordAccounts.getMail(offset, workRegion.profile)
    val gmail = GMails.getGMail(workRegion.profile)
    val password = DiscordAccounts.getPassword(offset, workRegion.profile)
    val token = DiscordAccounts.getToken(offset, workRegion.profile)
    workRegion.println(mail, foregroundGreen)
    workRegion.println(gmail, foregroundGreen)
    workRegion.println(password, foregroundGreen)
    workRegion.println(token, foregroundGreen)
    openRambler(workRegion, mail, password)
    screen.wait(10.0)
    browserNewTab(screen)
    openDiscord(screen, mail, password)
    screen.wait(10.0)
    browserNewTab(screen)
    browserOpenUrl(screen, "gmail.com")
    screen.wait(10.0)
}

suspend fun openDiscord(screen: Screen, mail: String, password: String) {
    browserOpenUrl(screen, "https://discord.com/login")
    browserWaitLoad(screen)
    val welcome = screen.wait("discord_welcome_back.png")
    screen.wait(1.0)
    welcome.setTargetOffset(0, 110)
    screen.queueTakeClick()
    screen.paste(mail)
    screen.queueRelease()
    welcome.setTargetOffset(0, 190)
    screen.queueTakeClick()
    screen.paste(password)
    screen.queueRelease()
    welcome.setTargetOffset(0, 280)
    screen.queueTakeClickRelease()
}