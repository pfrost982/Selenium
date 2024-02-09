package starknet

import ads_std.browserOpenUrl
import org.sikuli.script.Key
import org.sikuli.script.Pattern
import org.sikuli.script.Screen

fun starknetDiscordScript(screen: Screen, number: Int) {
    //browserOpenUrl(screen, "https://discord.com/invite/qypnmzkhbc")
    screen.wait("browser_accept_invite_button.png", 24.0)
    screen.click()
    screen.wait("browser_cancel_discord_app_button.png", 24.0)
    screen.click()
    screen.wait("browser_continue_to_discord_button.png")
    screen.click()
    println("Search discord news...")
    val news = screen.exists(Pattern("discord_close_big.png").similar(0.9), 12.0)
    if (news != null) {
        println("profile $number: News founded")
        screen.click()
    }
    println("Search discord close button...")
    val close = screen.exists("discord_close_button.png")
    if (close != null) {
        println("profile $number: Close button founded")
        screen.click()
    }
    println("Search discord maybe later button...")
    val maybeLater = screen.exists("discord_maybe_later_button.png")
    if (maybeLater != null) {
        println("profile $number: Maybe later button founded")
        screen.click()
    }
    screen.wait("discord_verify_button.png", 8.0)
    screen.click()
    screen.wait("discord_check_your_dms.png", 8.0)
    screen.click()


    println("Wait verification...")
    val verify = screen.exists("discord_you_have_been_verified.png", 64.0)
    if (verify != null) {
        println("profile $number: Verified!")
    } else {
        println("profile $number: Not verified!")
    }
    //browserOpenUrl(screen, "https://discord.com/channels/793094838509764618/884341617992024105")
    println("Search discord agree button...")
    val agree = screen.exists("discord_agree_button.png", 9.0)
    if (agree != null) {
        println("profile $number: Agree button founded")
        screen.click()
    }
    screen.wait("message_input.png", 12.0)
    screen.click()
    screen.write("gn")
    screen.type(Key.ENTER)

    println("profile $number: registration OK!")
    screen.wait(3.0)
}