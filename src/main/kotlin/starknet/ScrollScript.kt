package starknet

import ads_std.closeProfileWithoutDriver
import ads_std.openProfileWithoutDriver
import ads_std.openURLsikuliX
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Pattern
import org.sikuli.script.Screen

suspend fun starknetDiscordScript(number: Int) {
    isError = false
    openProfileWithoutDriver(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/starknet/png")
    try {
        openURLsikuliX(screen, "https://discord.com/invite/qypnmzkhbc")
        screen.wait("accept_invite_button.png", 24.0)
        screen.click()
        screen.wait("browser_cancel_discord_app_button.png", 24.0)
        screen.click()
        screen.wait("continue_to_discord_button.png")
        screen.click()
        val news = screen.exists(Pattern("discord_news_close.png").similar(0.9), 12.0)
        if (news != null) {
            println("profile $number: news case")
            screen.click()
        }
        screen.wait("privitive_start_channel.png")
        screen.click()
        val complete = screen.exists(Pattern("complete_button.png").similar(0.9), 5.0)
        if (complete != null) {
            println("profile $number: complete button case")
            screen.click()
            screen.wait(Pattern("agree_to_rules.png").targetOffset(0, 75))
            screen.click()
            screen.wait("read_and_agree_rules_checkbox.png")
            screen.click()
            screen.wait("submit_green_button.png")
            screen.click()
        } else {
            println("profile $number: without complete button case")
        }
        screen.wait("access_react_smile.png")
        screen.click()
        screen.wait("primitive_general_channel.png", 12.0)
        screen.click()
        println("profile $number: registration OK!")

        screen.wait(3.0)
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }

    profileWork = false
    if (isError) {
        errorList.add(number)
        println("profile $number: script ended with ERROR, added to error list")
        //closeProfileWithoutDriver(number)
    } else {
        println("profile $number script ended without errors")
        closeProfileWithoutDriver(number)
    }
}