package cap_monster

import ads_std.*
import org.sikuli.script.ImagePath
import org.sikuli.script.Key
import org.sikuli.script.Pattern

suspend fun addKeyAndSetRepeat(workRegion: WorkRegion) {
    ImagePath.add("src/main/kotlin/cap_monster/png")
    val screen = workRegion.screen
    screen.wait("browser_plus.png")
    screen.queueTakeClickRelease()
    browserOpenExtension(screen, Pattern("cap_monster.png"))
    val off = screen.exists(Pattern("cap_monster_off.png").similar(0.95), 5.0)
    if (off != null) {
        println("profile ${workRegion.profile}: Extension off")
        screen.queueTakeClickRelease()
        screen.wait(0.5)
    }

    val balanceOK = screen.exists(
        Pattern("cap_monster_balance_ok.png")
            .similar(0.8)
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
        screen.type("a", Key.CTRL)
        screen.paste(CAP_MONSTER_KEY)
        screen.queueRelease()

        screen.wait("cap_monster_save_key_button.png", 8.0)
        screen.queueTakeClickRelease()
    }

    screen.wait(
        Pattern("cap_monster_balance_ok.png")
            .similar(0.8), 5.0
    )
    println("profile ${workRegion.profile}: Balance OK")
    browserScroll(screen, 3)
    val three = screen.exists(Pattern("cap_monster_5.png").similar(0.95))
    if (three == null) {
        println("profile ${workRegion.profile}: Repeat not 5")
        screen.wait(Pattern("cap_monster_repeat_captcha.png").targetOffset(-40, 0))
        screen.queueTakeClick()
        screen.wait(Pattern("cap_monster_5.png").similar(0.95))
        screen.queueClickRelease()
        screen.wait(1.0)
    }
    screen.wait(Pattern("cap_monster_5.png").similar(0.95))
    println("profile ${workRegion.profile}: Repeat is 5")
}