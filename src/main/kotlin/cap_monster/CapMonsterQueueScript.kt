package cap_monster

import ads_std.*
import org.sikuli.script.FindFailed
import org.sikuli.script.Pattern

suspend fun addKeyScript(workRegion: WorkRegion) {
    val screen = workRegion.screen
    println(
        backgroundGreen + "Start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundBlack
    )
    try {
        screen.wait(4.0)
        var notOpened = true
        while (notOpened) {
            screen.wait(
                Pattern("favorites_and_extensions.png")
                    .targetOffset(20, 0)
                    .similar(0.95),
                8.0
            )
            println("profile ${workRegion.profile}: Open Cap monster")
            screen.queueTakeClick()
            val monsterCapExists = screen.exists("cap_monster.png")
            if (monsterCapExists != null) {
                screen.queueClickRelease()
                notOpened = false
            } else {
                screen.queueRelease()
            }
        }

        val balanceOK = screen.exists(
            Pattern("cap_monster_balance_ok.png")
                .similar(0.97), 5.0
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
            //screen.paste(CAP_MONSTER_KEY)
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

    } catch (e: FindFailed) {
        println(backgroundRed + "Profile ${workRegion.profile} error")
        e.printStackTrace()
        errorList.add(workRegion.profile)
    }
    println(
        backgroundGreen + "Finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundBlack
    )
}
