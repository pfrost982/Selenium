package discord

import org.sikuli.script.*

fun sikuliXScript() {
    println("start button search...")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/discord/png")
    while (true) {
        try {
            val acceptButton = screen.exists(Pattern("accept_invite_button.png").similar(0.9), 1.0)
            if (acceptButton != null) {
                println("accept button found\n")
                screen.click()
                screen.wait("browser_cancel_discord_app_button.png", 12.0)
                screen.click()
                screen.wait("continue_to_discord_button.png")
                screen.click()
                screen.wait("complete_button.png", 24.0)
                screen.click()
                screen.wait(Pattern("agree_to_rules.png").targetOffset(0, 75))
                screen.click()
                screen.wheel(Mouse.WHEEL_DOWN, 7)
                screen.wait("read_and_agree_rules_checkbox.png")
                screen.click()
                screen.wait("submit_green_button.png")
                screen.click()
                screen.wait("fuel_react_icon.png")
                screen.click()
                screen.wait("gm_gn_chanel.png")
                screen.click()
                screen.wait("message_input.png", 12.0)
                screen.write("gm")
                screen.type(Key.ENTER)
            } else {
                println("accept button not found\n")
            }
        } catch (e: FindFailed) {
            e.printStackTrace()
            isError = true
        }
    }
}