package fuel_wallet

import ads_std.*
import org.sikuli.script.*

suspend fun createWalletScript(workRegion: WorkRegion) {
    val screen = workRegion.screen
    println(
        backgroundGreen + "Start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundBlack
    )
    val seed = FuelSeeds.getSeed(workRegion.profile)
    try {
        openUrlSikuliDark(screen, "chrome-extension://keomgfjjdfafellomeiicgdkjnedoadc/index.html#/sign-up/welcome")
        val createWallet = screen.exists("i_have_wallet.png", 7.0)
        if (createWallet != null) {
            println("profile ${workRegion.profile}: create wallet case")
            screen.queueTakeClick()
            screen.type(Key.END, Key.CTRL)
            screen.queueRelease()
            screen.wait("fuel_i_accept_button.png")
            screen.queueTakeClickRelease()
            screen.wait(Pattern("fuel_i_have_12_words.png").similar(0.95).targetOffset(-180, 45))
            screen.queueTakeClick()
            screen.paste(seed)
            screen.queueRelease()
            screen.wait("fuel_next_button.png")
            screen.queueTakeClickRelease()
            screen.wait("type_password.png")
            screen.queueTakeClick()
            screen.paste(WALLET_PASS_UP)
            screen.queueRelease()
            screen.wait("confirm_password.png")
            screen.queueTakeClick()
            screen.paste(WALLET_PASS_UP)
            screen.queueRelease()
            screen.wait("fuel_next_button.png")
            screen.queueTakeClickRelease()
            screen.wait("wallet_created.png", 18.0)
            screen.wait("extensions.png")
            screen.queueTakeClick()
            screen.wait("fuel_wallet.png")
            screen.queueClickRelease()
        } else {
            screen.wait("wallet_created.png", 18.0)
            screen.wait("extensions.png")
            screen.queueTakeClick()
            screen.wait("fuel_wallet.png")
            screen.queueClickRelease()
            screen.wait("type_password.png")
            screen.queueTakeClick()
            screen.paste(WALLET_PASS_UP)
            screen.type(Key.ENTER)
            screen.queueRelease()
        }

        val faucet = screen.exists("fuel_faucet_button.png", 7.0)
        if (faucet != null) {
            println("profile ${workRegion.profile}: faucet case")
            screen.queueTakeClickRelease()
            screen.wait(
                Pattern("i_am_not_robot.png")
                    .similar(0.98), 60.0
            )
            screen.wait("give_me_ether.png")
            screen.queueTakeClickRelease()
            screen.wait("see_on_explorer.png", 8.0)
            screen.wait("extensions.png")
            screen.queueTakeClick()
            screen.wait("fuel_wallet.png")
            screen.queueClickRelease()
        }
        screen.wait(12.0)
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
