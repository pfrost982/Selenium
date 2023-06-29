package discord

import ads_std.*
import org.sikuli.script.Key
import org.sikuli.script.Mouse
import org.sikuli.script.Pattern
import org.sikuli.script.Screen
import java.io.File

val discord_names_file = File("src/main/kotlin/discord/discord_names.txt")
suspend fun cometaOpenDiscord(workRegion: WorkRegion) {
    val screen = workRegion.screen
    val token = DiscordTokens.getToken(workRegion.profile)
    println(token)
    screen.wait(2.0)
    openExtension(screen, Pattern("cometa_extension.png"))
    screen.wait(1.0)
    screen.wait(Pattern("cometa_login_discord.png").targetOffset(0, 55))
    screen.queueTakeClick()
    screen.paste(token)
    screen.queueRelease()
    screen.wait(1.0)
    screen.wait(Pattern("cometa_login_discord.png").targetOffset(0, 105))
    screen.queueTakeClickRelease()
    screen.wait("discord_settings_icon.png", 32.0)
    screen.wait(4.0)
    screen.wait("discord_settings_icon.png", 32.0)
    screen.wait(4.0)
    while (tryToClickQueue(screen, Pattern("discord_close_small_gray.png").similar(0.95))) {
        screen.wait(2.0)
    }
    //tryToClickQueue(screen, Pattern("discord_do_later.png"))
    //tryToClickQueue(screen, Pattern("discord_do_later_ru.png"))
    screen.wait(1.0)
    screen.wait(Pattern("discord_settings_icon.png").targetOffset(-140, 0))
    screen.queueTakeClickRelease()
    screen.wait("discord_edit_icon.png")
    screen.wait(0.5)
    screen.wait(Pattern("discord_edit_icon.png").targetOffset(-155, 125))
    screen.queueTakeClick()
    screen.wait(0.5)
    val name = getTextFromClipboard()
    fileAppendString(discord_names_file, "${workRegion.profile} $name")
    println("${workRegion.profile} $name")
    screen.queueRelease()
    screen.wait(3.0)
}

suspend fun discordScript(screen: Screen) {
    openUrlSikuliDark(screen, "https://discord.com/channels/916379725201563759/1010982393559121960")
    screen.wait(Pattern("chanel_icon.png").targetOffset(100, 100), 12.0)
    screen.mouseMove()
    screen.wheel(Mouse.WHEEL_UP, 8)
    screen.wheel(Mouse.WHEEL_DOWN, 16)
    screen.wait(2.0)
    screen.wait("message_input.png")
    screen.click()
    screen.write("gm")
    screen.type(Key.ENTER)
    screen.wait(3.0)
    //driver.switchTo().newWindow(WindowType.TAB)
    openUrlSikuliDark(screen, "https://discord.com/invite/xfpK4Pe")
    screen.wait("browser_accept_invite_button.png", 12.0)
    screen.click()
    screen.wait("browser_cancel_discord_app_button.png", 12.0)
    screen.click()
    screen.wait("browser_continue_to_discord_button.png")
    screen.click()
    screen.wait("complete_button.png", 12.0)
    screen.click()
    screen.wait(Pattern("agree_to_rules.png").targetOffset(0, 75))
    screen.mouseMove()
    screen.wheel(Mouse.WHEEL_DOWN, 8)
    screen.wait("submit_green_button.png")
    screen.click()
    screen.wait("read_and_agree_rules_checkbox.png")
    screen.click()
    screen.wait("fuel_react_icon.png")
    screen.click()
    openUrlSikuliDark(screen, "https://discord.com/channels/732892373507375164/955224381292937216")
    screen.wait("message_input.png", 12.0)
    screen.write("gm")
    screen.type(Key.ENTER)
    screen.wait(1.0)
}