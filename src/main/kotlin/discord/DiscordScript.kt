package discord

import ads_std.openProfile
import org.openqa.selenium.WindowType
import org.sikuli.script.*

suspend fun discordScript(number: Int) {
    val driver = openProfile(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/discord/png")
    try {
        driver.get("https://discord.com/channels/916379725201563759/1010982393559121960")
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
        driver.switchTo().newWindow(WindowType.TAB)
        driver.get("https://discord.com/invite/xfpK4Pe")
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
        driver.get("https://discord.com/channels/732892373507375164/955224381292937216")
        screen.wait("message_input.png", 12.0)
        screen.write("gm")
        screen.type(Key.ENTER)
        screen.wait(1.0)
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }

    profileWork = false
    if (isError) {
        driver.quit()
    } else {
        driver.quit()
        //closeProfile(number, driver)
    }
}