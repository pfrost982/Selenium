package discord

import org.sikuli.script.*

fun sikuliXScript(screen: Screen) {
    println("start button search...")
    val acceptButton = screen.exists(Pattern("browser_accept_invite_button.png").similar(0.9), 1.0)
    if (acceptButton != null) {
        println("!!!accept button found")
        screen.click()
        screen.wait("browser_cancel_discord_app_button.png", 12.0)
        screen.click()
        screen.wait("browser_continue_to_discord_button.png")
        screen.click()
        screen.wait("complete_button.png", 24.0)
        screen.click()
        screen.wait(Pattern("agree_to_rules.png").targetOffset(0, 75))
        screen.click()
        screen.wheel(Mouse.WHEEL_DOWN, 7)
        screen.wait(0.7)
        screen.wait("read_and_agree_rules_checkbox.png")
        screen.click()
        screen.wait("submit_green_button.png")
        screen.click()
        screen.wait("moralis_verify_button.png")
        screen.click()
    }
    val verified = screen.exists(Pattern("moralis_changelog_channel.png").similar(0.9), 1.0)
    if (verified != null) {
        println("!!!verified")
        screen.click()
        screen.wheel(Mouse.WHEEL_DOWN, 8)
        screen.wait(0.7)
        screen.wait("moralis_gm_channel.png", 12.0)
        screen.click()
        screen.wait("message_input.png", 12.0)
        screen.write("gm")
        screen.type(Key.ENTER)
    }
    println("scan...")
}