package primitive

import ads_std.*
import org.sikuli.script.*

suspend fun primitiveScript(number: Int) {
    val screen = Screen()
    screen.wait(2.0)
    //driver.get("https://primitive.xyz/")
    screen.wait(1.0)
    screen.wait("email_input.png")
    screen.click()
    //screen.paste(mail)
    screen.wait("mail_button")
    screen.click()
    /*
            screen.wait(2.0)
            driver.get("https://primitive.xyz/")
            screen.wait(1.0)
            screen.wait("email_input.png")
            screen.click()
            insertTextTroughClipboard(screen, twitName)
            screen.wait("twit_button")
            screen.click()
            screen.wait(2.0)
    */
    /*
            driver.get("https://discord.com/invite/primitive")
            screen.wait(1.0)
            screen.wait("accept_invite")
            screen.click()
    */
    screen.wait(3.0)

}

suspend fun discordRegistrationScript(number: Int) {
    val screen = Screen()
    screen.wait("browser_address_input.png", 24.0)
    screen.click()
    screen.paste("https://discord.com/invite/primitive")
    screen.type(Key.ENTER)
    screen.wait("browser_accept_invite_button.png", 24.0)
    screen.click()
    screen.wait("browser_cancel_discord_app_button.png", 24.0)
    screen.click()
    screen.wait("browser_continue_to_discord_button.png")
    screen.click()
    val news = screen.exists(Pattern("discord_close_big.png").similar(0.9), 12.0)
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
}