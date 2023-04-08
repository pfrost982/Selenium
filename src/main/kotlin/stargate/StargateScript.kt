package stargate

import ads_std.*
import okhttp3.internal.wait
import org.sikuli.script.FindFailed
import org.sikuli.script.ImagePath
import org.sikuli.script.Key
import org.sikuli.script.Pattern
import org.sikuli.script.Screen
import kotlin.random.Random

suspend fun stargateGuildScript(number: Int) {
    isError = false
    val screen = Screen()
    ImagePath.add("src/main/kotlin/stargate/png")
    try {
        openProfileWithoutDriver(number, 0, 0, 960, 1050)
        screen.x = 0
        screen.y = 0
        screen.w = 1580
        screen.h = 1050
        println(screen.bounds)
        println(screen.w)
        println(screen.h)
        println("profile $number: start script on thread ${Thread.currentThread().name}")
        openURLsikuliXDark(screen, "https://guild.xyz/stargate")
        screen.wait(7.0)
        screen.wait("guild_connect_wallet.png")
        screen.click()
        screen.wait("guild_metamask_button.png")
        screen.click()
        val metamaskLanguage = metamaskUnlock(screen)
        println("Metamask language = $metamaskLanguage")
/*
        screen.wait(3.0)
        if (metamaskIsOpenedDark(screen, 3.0)) {
            metamaskGotIt(screen, metamaskLanguage)
            metamaskNext(screen, metamaskLanguage)
            metamaskConnect(screen, metamaskLanguage)
            screen.wait(3.0)
        }
        val verifyAccount = screen.exists("guild_verify_account.png")
        if (verifyAccount != null) {
            println("Verify account case")
            screen.click()
            metamaskSign(screen, metamaskLanguage)
        } else {
            println("No verify account case")
        }
*/
        val join = screen.exists("guild_join_to_get_roles.png", 9.0)
        if (join != null) {
            println("Join case")
            screen.wait(1.0)
            screen.click()
            val connect = screen.exists(Pattern("guild_connect.png").similar(0.95))
            if (connect != null) {
                println("Connect discord case")
                screen.click()
                screen.wait("guild_authorize.png", 12.0)
                screen.wait(1.0)
                screen.click()
                screen.wait(7.0)
            } else {
                println("Discord connected")
            }
            screen.wait("guild_join.png")
            screen.click()
            screen.wait(3.0)
        } else {
            println("No join case")
        }
        screen.wait(Pattern("guild_member.png").similar(0.95), 8.0)
        screen.wait(Pattern("guild_vestaker_ok.png").similar(0.95))
        println("veStaker OK!")
        screen.wait(3.0)
    } catch (e: FindFailed) {
        e.printStackTrace()
        isError = true
    }

    profileWork = false
    if (isError) {
        errorList.add(number)
        println("profile $number: ended with ERROR, added to error list")
        closeProfileWithoutDriver(number)
    } else {
        println("profile $number Success!")
        closeProfileWithoutDriver(number)
    }
}

suspend fun stargateDiscordScript(number: Int) {
    zksync.isError = false
    openProfileWithoutDriver(number)
    println("profile $number: start script on thread ${Thread.currentThread().name}")
    val screen = Screen()
    ImagePath.add("src/main/kotlin/zksync/png")
    try {
        openURLsikuliX(screen, "https://join.zksync.dev/")
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
        openURLsikuliX(screen, "https://discord.com/channels/722409280497516566/1052296324218892318")
        screen.wait("discord_read_rules_button.png", 12.0)
        screen.click()
        openURLsikuliX(screen, "https://discord.com/channels/722409280497516566/829191292951986186")
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
        zksync.isError = true
    }

    zksync.profileWork = false
    if (zksync.isError) {
        zksync.errorList.add(number)
        println("profile $number: script ended with ERROR, added to error list")
        //closeProfileWithoutDriver(number)
    } else {
        println("profile $number script ended without errors")
        closeProfileWithoutDriver(number)
    }
}