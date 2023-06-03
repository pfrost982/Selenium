package starknet

import ads_std.*
import org.sikuli.script.FindFailed
import org.sikuli.script.Key
import org.sikuli.script.Pattern

suspend fun createBraavosWalletScript(workRegion: WorkRegion) {
    val screen = workRegion.screen
    val seed = StarknetSeeds.getSeed(workRegion.profile)
    println(
        backgroundGreen + "Start profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundBlack
    )
    try {
        screen.wait(3.0)
        openExtension(screen, Pattern("braavos_wallet.png"))
        screen.wait("braavos_import_wallet.png")
        screen.queueTakeClick()
        screen.paste(seed)
        screen.queueRelease()
        screen.wait("braavos_next_button.png")
        screen.paste(WALLET_PASS)
        screen.type(Key.TAB)
        screen.paste(WALLET_PASS)
        screen.type(Key.ENTER)
        screen.queueTakeClickRelease()
        screen.wait(3.0)
    } catch (e: FindFailed) {
        println(backgroundRed + "Profile ${workRegion.profile} error")
        e.printStackTrace()
        if (workQueue.peek() == screen) {
            workQueue.poll()
        }
        errorList.add(workRegion.profile)
    }
    println(
        backgroundGreen + "Finish profile ${workRegion.profile}, line: ${workRegion.line}, row: ${workRegion.row}"
                + backgroundBlack
    )
}
/*

private suspend fun openBraavosWallet(screen: Screen) {
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
*/
