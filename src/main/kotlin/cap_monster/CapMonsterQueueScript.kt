package cap_monster

import ads_std.*
import org.sikuli.script.Pattern

suspend fun addKeyScript(workRegion: WorkRegion) {
    val screen = workRegion.screen
    screen.wait(2.0)
    openExtension(screen, Pattern("cap_monster.png"))
    val off = screen.exists("cap_monster_off.png", 5.0)
    if (off != null) {
        println("profile ${workRegion.profile}: Extension off")
        screen.queueTakeClickRelease()
        screen.wait(0.5)
    }

    val balanceOK = screen.exists(
        Pattern("cap_monster_balance_ok.png")
            .similar(0.97)
    )
    if (balanceOK == null) {
        println("profile ${workRegion.profile}: Balance empty")
        screen.wait(
            Pattern("cap_monster_api_key_input_icon.png")
                .targetOffset(20, 0)
                .similar(0.95),
            8.0
        )
        println("profile ${workRegion.profile}: Input key")
        screen.queueTakeClick()
        screen.paste(CAP_MONSTER_KEY)
        screen.queueRelease()

        screen.wait("cap_monster_save_key_button.png", 8.0)
        screen.queueTakeClickRelease()
    }

    screen.wait(
        Pattern("cap_monster_balance_ok.png")
            .similar(0.97), 5.0
    )
    println("profile ${workRegion.profile}: Balance OK")
    screen.wait(3.0)
}