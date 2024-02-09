package layer_zero

import ads_std.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sikuli.script.Key
import org.sikuli.script.Pattern
import java.io.File

val error_profiles_file = File("src/main/kotlin/layer_zero/error_profiles.txt")

suspend fun layerZeroGM(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    browserOpenUrl(screen, "https://discord.com/channels/881985666265780274/881990703633281034")
    val gmBranch = screen.exists(Pattern("discord_layer_zero.png").targetOffset(0, 50), 14.0)
    if (gmBranch != null) {
        screen.wait(4.0)
        screen.wait(Pattern("discord_layer_zero.png").targetOffset(0, 50))
        screen.queueTakeClickRelease()
        screen.wait(Pattern("discord_message_gm.png").targetOffset(-64, 0).similar(0.95))
        screen.queueTakeClick()
        screen.paste("gm")
        screen.type(Key.ENTER)
        screen.queueRelease()
        println("Profile ${workRegion.profile}: GM")
        screen.wait(3.0)
    } else {
        fileAppendString(error_profiles_file, "${workRegion.profile}")
        println("Profile ${workRegion.profile}: No branch")
        screen.wait(3.0)
    }
}

suspend fun inviteDiscord(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    browserOpenUrl(screen, "https://discord-layerzero.netlify.app/discord")
    screen.wait("invite_join.png", 120.0)
    screen.queueTakeClickRelease()

    var language: String? = null
    val en = CoroutineScope(Dispatchers.Default).launch {
        val invite = screen.exists("discord_accept_invite", 24.0)
        if (invite != null) {
            language = "en"
        }
    }
    val ru = CoroutineScope(Dispatchers.Default).launch {
        val invite = screen.exists("discord_accept_invite_ru", 24.0)
        if (invite != null) {
            language = "ru"
        }
    }
    while (language == null) {
        delay(500)
    }
    en.cancel()
    ru.cancel()
    println("Profile ${workRegion.profile}: language = $language")
    var suffix = ""
    if (language == "ru") {
        suffix = "_ru"
    }

    var openDiscord: Boolean? = null
    var tryCount = 0
    while (openDiscord != true && tryCount < 5) {
        openDiscord = null
        tryCount++
        screen.wait("discord_accept_invite" + suffix + ".png", 8.0)
        screen.queueTakeClickRelease()
        screen.wait(3.0)
        val pass = CoroutineScope(Dispatchers.Default).launch {
            val result = screen.exists("discord_complete" + suffix + ".png", 120.0)
            if (result != null) {
                openDiscord = true
            }
        }
        val notPass = CoroutineScope(Dispatchers.Default).launch {
            val result = screen.exists("discord_accept_invite" + suffix + ".png", 120.0)
            if (result != null) {
                openDiscord = false
            }
        }
        var timeCount = 0
        while (openDiscord == null) {
            delay(1000)
            timeCount++
            if (timeCount > 120) {
                println("Profile ${workRegion.profile}: Capmonster got lost")
                openDiscord = true
                break
            }
        }
        pass.cancel()
        notPass.cancel()
        println("Profile ${workRegion.profile}: $openDiscord")
    }
    screen.wait(3.0)
    screen.wait(Pattern("discord_layer_zero.png").targetOffset(0, 50), 8.0)
    screen.queueTakeClickRelease()
    screen.wait(2.0)
    screen.wait("discord_complete" + suffix + ".png")
    screen.queueTakeClickRelease()
    screen.wait("discord_check_box.png", 8.0)
    screen.queueTakeClickRelease()
    screen.wait("discord_submit_button" + suffix + ".png")
    screen.queueTakeClickRelease()
    screen.wait("discord_layer_zero.png")
    println("Profile ${workRegion.profile}: OK!")
    screen.wait(2.0)
}