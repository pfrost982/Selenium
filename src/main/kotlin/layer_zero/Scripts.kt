package layer_zero

import ads_std.WorkRegion
import ads_std.openUrlSikuliDark
import ads_std.queueTakeClickRelease
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.sikuli.script.Pattern

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