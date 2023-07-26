package layer_zero

import ads_std.WorkRegion
import ads_std.openUrlSikuliDark
import ads_std.queueTakeClickRelease
import ads_std.tryToClickQueue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sikuli.script.Pattern
import java.lang.Exception

suspend fun inviteDiscord(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    openUrlSikuliDark(screen, "https://discord-layerzero.netlify.app/discord")
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
    while (openDiscord != true) {
        openDiscord = null
        screen.wait("discord_accept_invite" + suffix + ".png", 8.0)
        screen.queueTakeClickRelease()
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
        var count = 0
        while (openDiscord == null) {
            delay(1000)
            count++
            if (count > 120) {
                throw Exception("Capmonster got lost")
            }
        }
        pass.cancel()
        notPass.cancel()
        println("Profile ${workRegion.profile}: $openDiscord")
    }
    screen.wait(3.0)
    tryToClickQueue(screen, Pattern("discord_close_dark_gray.png"), 8.0)
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