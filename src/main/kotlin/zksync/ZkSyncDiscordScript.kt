package zksync

import ads_std.closeProfileWithoutDriver
import ads_std.openProfileWithoutDriver
import org.sikuli.script.*
import kotlin.random.Random

suspend fun zkSyncDiscordScript(number: Int) {
    isError = false
    openProfileWithoutDriver(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/zksync/png")
    try {
        //openUrlSikuli(screen, "https://join.zksync.dev/")
        screen.wait("browser_not_robot_checkbox.png", 12.0)
        screen.wait(3.0)
        screen.wait("browser_not_robot_checkbox.png")
        screen.click()
        screen.wait("browser_accept_invite_button.png", 180.0)
        screen.wait(3.0)
        screen.wait("browser_accept_invite_button.png")
        screen.click()
        screen.wait("browser_cancel_discord_app_button.png", 24.0)
        screen.click()
        screen.wait("browser_continue_to_discord_button.png")
        screen.click()
        println("Wait agree button...")
        val agree = screen.exists("discord_agree_button.png", 12.0)
        if (agree != null) {
            println("profile $number: Agree button founded")
            screen.click()
        }
        //openUrlSikuli(screen, "https://discord.com/channels/722409280497516566/1052296324218892318")
        screen.wait("discord_read_rules_button.png", 12.0)
        screen.click()
        //openUrlSikuli(screen, "https://discord.com/channels/722409280497516566/829191292951986186")
        screen.wait(3.0)
        screen.wait("message_input.png", 12.0)
        screen.click()
        screen.write(
            listOf("gm", "gn", "Gm", "Gn", "GM", "GN")[Random.nextInt(6)]
        )
        screen.type(Key.ENTER)
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